def main():
    index1 = 0
    index2 = 0
    total = 0
    num_list = [100.7, 20.6, 7.6, 89.4, 93.9, 23, 47, 66, 12]
    writefile = open('calculations.txt', 'w')  # create file

    while index1 < len(num_list):
        num_list[index1] = str(num_list[index1]) + '\n'  # convert floats/ints to strings that will jump to next line
        writefile.write(num_list[index1])  # write items from converted list to file
        index1 += 1

    writefile.close()

    try:
        readfile = open('calculations.txt', 'r')
        readlist = list(readfile.readlines())
        readfile.close()
    except IOError:  # exception for file corruption, inability to create earlier in program, or inability to read for some reason
        print("Error creating or reading the text file. Please restart the program and try again.")

    openlist = [i.rstrip('\n') for i in readlist]

    while index2 < len(openlist):
        openlist[index2] = float(openlist[index2])  # convert string list to float list
        index2 += 1

    for i in openlist:
        total += i  # add all elements

    listsum = total  # perform calculations
    try:
        avg = total / len(openlist)
    except ZeroDivisionError:  # empty list causes division by zero here since len(openlist) would be zero
        print("Empty list. Please ensure the text file has not been modified by another program, and try again.")
    maximum = max(openlist)
    minimum = min(openlist)

    print(f'Sum: {listsum:.2f}')  # print outputs
    print(f'Average: {avg:.2f}')
    print(f'Maximum: {maximum}')
    print(f'Minimum: {minimum}')

    appendfile = open('calculations.txt', 'a')

    appendfile.write(f'Sum: {listsum:.2f}\n')  # append outputs to text file
    appendfile.write(f'Average: {avg:.2f}\n')
    appendfile.write(f'Maximum: {maximum}\n')
    appendfile.write(f'Minimum: {minimum}')

    appendfile.close()


main()