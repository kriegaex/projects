import time

def is_even(num):
    if num % 2 == 0:
        return True
    else: return False

def factorial(num):
    if num == 0:
        return 1
    else:
        return factorial(num - 1) * num

def formula(num1, num2):
    combination = factorial(num1) / (factorial(num2) * factorial(num1 - num2))
    return combination

def main():
    index = 2
    count = 0
    while index <= 100:
        if not is_even(index):
            for i in range(1, int(0.5 * index) + 1):
                if formula(index, i) > 1000000:
                    count += (int(0.5 * index) + 1 - i) * 2
                    break
        else:
            for i in range(1, int(0.5 * (index + 1)) + 1):
                if formula(index, i) > 1000000:
                    count += (int(0.5 * (index + 1)) + 1 - i) * 2 - 1
                    break
        index += 1
    print(count)

s = time.time()
main()
e = time.time()
print(e - s)