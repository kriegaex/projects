from uint_prime import isprime
import time


def remove(number):
    rotation = number
    string = str(number)
    '''for i in string:
        if i in ['2', '4', '5', '6', '8','0']:
            return False'''
    for i in range(len(str(rotation))):
        rotation = (rotation % 10) * (10 ** (len(str(rotation)) - 1)) + rotation // 10
        if not isprime(rotation):
            return False
    return True


def main():
    list = []
    for i in range(2, 1000000):
        if isprime(i):
            if remove(i):
                list.append(i)
    print(len(list))


start = time.time()
main()
end = time.time()
print(end - start)
