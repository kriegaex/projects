import time
start = time.time()

def istriangular(number):
    x = 0.5 * ((8 * number + 1) ** 0.5 - 1)
    if x % 1 == 0:
        return True

def ispentagonal(number):
    x = ((24 * number + 1) ** 0.5 + 1) / 6
    if x % 1 == 0:
        return True

def ishexagonal(number):
    x = 0.25 * ((8 * number + 1) ** 0.5 + 1)
    if x % 1 == 0:
        return True

def generate_hexagonal(index):
    number = index * (index * 2 - 1)
    return number

def main():
    s = 143
    while True:
        s += 1
        number = generate_hexagonal(s)
        if ispentagonal(number):
            if istriangular(number):
                print(number)
                return True
main()
end = time.time()
print(end - start)

