def contain9numbers(number):
    l = list(str(number))
    l .sort()
    if l == ['1', '2', '3', '4', '5', '6', '7', '8', '9']:
        return True


first = 9
digit = 1
Pandigital = []

while digit <= 5:
    for i in range(1, 10 ** (digit - 1)):
        number = i + 9 * (10 ** (digit - 1))
        index = 1
        string = ''
        while len(string) < 9:
            string = string + str(number * index)
            index += 1
        if len(string) == 9:
            if contain9numbers(int(string)):
                Pandigital.append(string)
    digit += 1

print(Pandigital)

