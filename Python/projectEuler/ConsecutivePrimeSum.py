from uint_prime import isprime
import time
a= time.time()
def prime_list(upper_bound):
    a = []
    for i in range(1, upper_bound):
        if isprime(i):
            a.append(i)
    return a

list1 = prime_list(10000)

def longest(list):
    if isprime(sum(list)):
        return len(list)
    else:
        return longest(list[:len(list) - 1])

def main():
    answear_terms = 0
    for start in range(len(list1)):
        total = 0
        for i in range(start, len(list1)):
            total += list1[i]
            if total > 1000000:
                end = i
                number_of_terms = longest(list1[start:end])
                break
        if number_of_terms > answear_terms:
            answear_terms = number_of_terms
            total = sum(list1[start:end])
            print("answear_terms:",  answear_terms)
            print(total)
main()
b = time.time()
print(b - a)