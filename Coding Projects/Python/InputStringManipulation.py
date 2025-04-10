def reverse_and_space_uppercase_vowels(string):
    count = 0
    stringin = string
    newstring = ''
    revstring = ''

    for i in string:
        if i == 'a' or i == 'e' or i == 'i' or i == 'o' or i == 'u':
            newstring += i.upper() + ' '
        else:
            newstring += i.lower()

    for i in range(len(newstring)):
        revstring += newstring[len(newstring) - 1 - i]

    return revstring


userstring = input('Type something to rearrange: ')

reverse_and_space_uppercase_vowels(userstring)