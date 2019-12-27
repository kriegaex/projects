import time
from uint_prime import isprime
start = time.time()


##http://www.cnblogs.com/theskulls/p/4671936.html   答案的解释



def model1(index):
    ##a, b, c, d, e, f, g, h, i = "aaabbb", "aababb", "aabbab", "abaabb", "abbaab", "baaabb", "bbaaab", "babaab", "baabab"
    model_list = ["aaabbb", "aababb", "aabbab", "abaabb", "abbaab", "ababab"]
    return model_list[index]

def model2(index):
    model_list = ["baaabb", "bbaaab", "babaab", "baabab"]
    return model_list[index]

def transform(model_string, number, change_num):
    number_string = str(number)
    key = str(change_num)
    for i in range(len(model_string)):
        if model_string[i] == 'a':
            number_string = change_string(number_string, i, key)
    return int(number_string)

def change_string(string, index, object):
    string_list = list(string)
    string_list[index] = object
    s = ''.join(string_list)
    return s

def main():
    for i in range(100000, 1000000):
        last_digit = i % 10
        if last_digit in [1, 3, 7, 9]:
            for model_index in range(6):
                count = 0
                non_count = 0
                for change_to in range(1, 10):
                    test = transform(model1(model_index), i, change_to)
                    if isprime(test):
                        count += 1
                    else: non_count += 1
                    if non_count > 1:
                        break
                if count == 8:
                    print(i)
                    for change_to in range(1, 3):
                        if isprime(transform(model1(model_index), i, change_to)):
                            print(transform(model1(model_index), i, change_to))
                            break
                    return True
            for model_index in range(4):
                count = 0
                non_count = 0
                for change_to in range(0, 10):
                    if isprime(transform(model2(model_index), i, change_to)):
                        count += 1
                    else:non_count += 1
                    if non_count > 2:
                        break
                if count == 8:
                    print(i)
                    if isprime(transform(model2(model_index), i, change_to)):
                        print(transform(model2(model_index), i, change_to))
                        break
                    return True
main()
end = time.time()
print(end -start)


