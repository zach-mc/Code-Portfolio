import sqlite3

conn = sqlite3.connect('Library.db')
cur = conn.cursor()

cur.execute('PRAGMA foreign_keys= ON')

# create three tables
cur.execute('''
            CREATE TABLE IF NOT EXISTS Books (book_id INTEGER PRIMARY KEY NOT NULL, 
            title TEXT, 
            author TEXT)
            ''')

cur.execute('''
            CREATE TABLE IF NOT EXISTS Members (member_id INTEGER PRIMARY KEY NOT NULL, 
            name TEXT, 
            email TEXT)
            ''')

cur.execute('''
            CREATE TABLE IF NOT EXISTS Loans (loan_id INTEGER PRIMARY KEY NOT NULL, 
            book_id INTEGER,
            member_id INTEGER,
            due_date TEXT,
            FOREIGN KEY(book_id) REFERENCES Books(book_id), 
            FOREIGN KEY(member_id) REFERENCES Members(member_id))
            ''')

conn.commit()


def add_book(title, author):
    exists = False

    cur.execute('''
                SELECT title, author FROM Books
                ''')

    booklist = cur.fetchall()

    for i in booklist:
        if str(i[0]).lower() == title.lower() and str(i[1]).lower() == author.lower():
            exists = True
            print('Book already exists.')
        else:
            continue

    if exists == False:
        cur.execute('''
                INSERT INTO Books(title, author)
                VALUES(?,?)
                ''', (title, author))
        conn.commit()
        print('Book successfully added.')


def add_member(name, email):
    exists = False

    cur.execute('''
                SELECT email FROM Members
                ''')

    memberlist = cur.fetchall()

    for i in memberlist:
        if str(i[0]) == email:
            exists = True
            print('Email already in use.')
        else:
            continue

    if exists == False:
        cur.execute('''
                INSERT INTO Members(name, email)
                VALUES(?, ?)
                ''', (name, email))
        conn.commit()

        cur.execute('''
                SELECT * FROM Members
                ''')

        getnewid = cur.fetchall()

        for i in getnewid:
            if i[2] == email:
                newid = i[0]

        print(f'Member successfully added. Your Member ID is {newid}.')


def borrow_book(book_id, member_id, due_date):
    testcontrol = True

    cur.execute(''' 
                SELECT book_id FROM Loans
                ''')

    results = cur.fetchall()

    cur.execute('''
                SELECT book_id FROM Books
                ''')

    bookids = cur.fetchall()
    idlist = [int(i[0]) for i in bookids]

    cur.execute('''
                SELECT member_id FROM Members
                ''')

    memberlist = cur.fetchall()
    membermaxcheck = [int(i[0]) for i in memberlist]

    for i in results:
        if i[0] != book_id:
            continue
        elif i[0] == book_id:
            testcontrol = False

    if testcontrol == True:
        if 0 < book_id <= max(idlist) and 0 < member_id <= max(membermaxcheck):
            cur.execute('''
                        INSERT INTO Loans(book_id, member_id, due_date)
                        VALUES(?,?,?)
                        ''', (book_id, member_id, due_date,))
            conn.commit()

            cur.execute('''
                        SELECT * FROM Loans
                        ''')

            getloanid = cur.fetchall()

            for i in getloanid:
                if i[1] == book_id:
                    print(f'Book successfully checked out. Your Loan ID is {i[0]}, and your book is due {i[3]}.')
        elif book_id > max(idlist):
            print('Book ID invalid. Please try again with a different ID.')
        elif member_id > max(memberlist):
            print('Member ID invalid. Please try again with a different ID.')
        else:
            print('ID error. Please try again.')

    else:
        print('That book is already checked out.')

    conn.commit()


def return_book(loan_id):
    flag = False

    cur.execute('''
                SELECT loan_id FROM Loans
                ''')

    results = cur.fetchall()
    loanlist = [int(i[0]) for i in results]

    for i in results:
        if int(i[0]) == loan_id:
            flag = True
        elif int(i[0]) != loan_id:
            continue
        else:
            print('Problem searching for ID. Try another ID.')
    if flag == True:
        cur.execute('''
                    DELETE FROM Loans WHERE loan_id = ?
                    ''', (loan_id,))
        print('Book successfully returned.')
    else:
        print('Loan ID not found. Please try a different ID.')

    conn.commit()


def list_available_books():
    available = 'Available books: '

    cur.execute('''
                SELECT * FROM Books
                ''')
    results = cur.fetchall()

    cur.execute('''
                SELECT book_id from Loans
                ''')
    compare = cur.fetchall()

    for i in results:
        flag = True
        for j in compare:
            if i[0] == j[0]:
                flag = False
            elif i[0] != j[0]:
                continue
        if flag == True and f"(ID: {i[0]})" not in available:
            available += f"{i[1]} - {i[2]} (ID: {i[0]}), "

    try:
        available = available.rstrip(', ')
    except:
        print('Formatting error, continuing...')

    print(available)


def list_active_loans():
    activeloans = 'Books on loan: '

    cur.execute('''
                SELECT * FROM LOANS
                ''')
    loans = cur.fetchall()

    cur.execute('''
                SELECT * FROM Books
                ''')
    books = cur.fetchall()

    for i in loans:
        for j in books:
            if i[1] == j[0]:

                cur.execute('''
                            SELECT * FROM Members
                            ''')
                memberlist = cur.fetchall()
                for k in memberlist:
                    if k[0] == i[2]:
                        name = k[1]

                activeloans += f'Loan ID: {i[0]}, "{j[1]}" - Book ID: {j[0]}, Member Name: {name}, Member ID: {i[2]}, Due Date: {i[3]}. '
            else:
                continue
    print(activeloans)


def main():
    cont = 'y'
    choice = ''

    while cont.lower() == 'y':
        try:

            choice = input(
                'What would you like to do? (add book / add member / borrow book / return book / list books / list loans / quit): ')

            if choice.lower() == 'add book':
                title = input("What is the book's title? ")
                author = input("Who is the book's author? ")
                add_book(title, author)
                cont = input('Would you like to perform more operations? (y/n): ')
                if cont.lower() != 'y' and cont.lower() != 'n':
                    cont = input('Invalid input. Please select again (y/n): ')

            elif choice.lower() == 'add member':
                name = input("What is the new member's name? ")
                email = input("What is the new member's email address? ")
                add_member(name, email)
                cont = input('Would you like to perform more operations? (y/n): ')
                if cont.lower() != 'y' and cont.lower() != 'n':
                    cont = input('Invalid input. Please select again (y/n): ')

            elif choice.lower() == 'borrow book':
                book_id = int(input("What is the book ID of the book to borrow? "))
                member_id = int(input("What is the member ID of the borrowing member? "))
                due_date = input("When will the book be due? ")
                borrow_book(book_id, member_id, due_date)
                cont = input('Would you like to perform more operations? (y/n): ')
                if cont.lower() != 'y' and cont.lower() != 'n':
                    cont = input('Invalid input. Please select again (y/n): ')

            elif choice.lower() == 'return book':
                loan_id = int(input("What is the loan ID of the borrowed book? "))
                return_book(loan_id)
                cont = input('Would you like to perform more operations? (y/n): ')
                if cont.lower() != 'y' and cont.lower() != 'n':
                    cont = input('Invalid input. Please select again (y/n): ')

            elif choice.lower() == 'list books':
                list_available_books()
                cont = input('Would you like to perform more operations? (y/n): ')
                if cont.lower() != 'y' and cont.lower() != 'n':
                    cont = input('Invalid input. Please select again (y/n): ')

            elif choice.lower() == 'list loans':
                list_active_loans()
                cont = input('Would you like to perform more operations? (y/n): ')
                if cont.lower() != 'y' and cont.lower() != 'n':
                    cont = input('Invalid input. Please select again (y/n): ')

            elif choice.lower() == 'quit':
                cont = 'n'

            else:
                print('Command not found.')
                cont = input('Would you like to perform more operations? (y/n): ')
                if cont.lower() != 'y' and cont.lower() != 'n':
                    cont = input('Invalid input. Please select again (y/n): ')

        except sqlite3.Error as err:
            print(err)
            cont = input('An error has occurred. Would you like to try another operation? (y/n): ')

    print('Thank you for using this DBMS. Goodbye!')


main()

conn.close()