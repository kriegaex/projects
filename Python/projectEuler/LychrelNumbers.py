import time

def is_even(num):
    if len(str(num)) % 2 == 0:
        return True
    else: return False

def palindromic(number):
    string = str(number)
    if is_even(number):
        for i in range(int(0.5 * len(string))):
            if string[i] != string[len(string) - 1 - i]:
                return False
    else:
        for i in range(int(0.5 * (len(string) - 1)) ):
            if string[i] != string[len(string) - 1 - i]:
                return False
    return True

def reverse(num):
    list1 = list(str((num)))
    list2 = [int(i) for i in reversed(list1)]
    string = ''.join(map(str, list2))
    return int(string)

def transform(num):
    num += reverse(num)
    return num

def main():
    count = 0
    for i in range(100, 10001):
        find = False
        a = i
        for j in range(50):
            a = transform(a)
            if palindromic(a):
                find = True
                break
        if find == False:
            count += 1
    print(count)

start = time.time()
main()
end = time.time()
print(end - start)