def generate_list(type):
    n = 1
    result = []
    while True:
        if type == 'triangle':
            number = n * (n + 1) / 2
        elif type == 'square':
            number = n ** 2
        elif type == 'pentagonal':
            number = n * (3 * n - 1) / 2
        elif type == 'hexagonal':
            number = n * (2 * n - 1)
        elif type == 'heptagonal':
            number = n * (5 * n - 3) / 2
        elif type == 'octagonal':
            number  = n * (3 * n - 2)
        if number >= 10000:
            return result
        if 1000 <= number:
            result.append(int(number))
        n += 1

triangle_list = generate_list('triangle')
square_list = generate_list('square')
pentagonal_list	= generate_list('pentagonal')
hexagonal_list = generate_list('hexagonal')
heptagonal_list = generate_list('heptagonal')
octagonal_list = generate_list('octagonal')
big_list = [triangle_list, square_list, pentagonal_list, hexagonal_list, heptagonal_list, octagonal_list]


def confirm_type(num, dict1):
    for index in range(len(big_list)):
        if num in big_list[index]:
            dict1[index] += 1
        for i in range(len(dict1)):
            if dict1[i] > 1:
                return False
    return True

'''def is_cyclical(num1, num2):
    if (num1 % 100 == int(num2 / 100)):
        return True
    else:
        return False'''
def change_dict(num, dict):
    for index in range(len(big_list)):
        if num in big_list[index]:
            dict[index] += 1
    return dict


def find_6(first, result):
    instant1 = first % 100
    for i in range(10, 100):
        instant2 = int(str(instant1) + str(i))
        dict1 = {0: 0, 1: 0, 2: 0, 3: 0, 4: 0, 5: 1}
        if confirm_type(instant2, dict1):
            result.append(instant2)
            dict1 = change_dict(instant2, dict1)
            instant1 = instant2 % 100
        else: break
        if len(result) == 6:
            if instant2 % 100 == first // 100:
                return True
            break
        print(dict1)

def main():
    for i in octagonal_list:
        answear = [i]
        if find_6(i, answear):
            print(answear)
            return True
#main()
dict = {0: 0, 1: 0, 2: 0, 3: 0, 4: 0, 5: 1}
change_dict(45, dict)
print(dict)



