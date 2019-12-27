from into_binary import binary

def ispalindrome(number):
    string = str(number)
    if string[:1] == string[-1:]:
        if len(string) <= 1:
            return True
        else:
            return ispalindrome(string[1:-1])
    return False

list = []

def main():
    for i in range(1, 1000000):
        if ispalindrome(i):
            convert = binary(i)
            if ispalindrome(convert):
                list.append(i)
    print(sum(list))
main()