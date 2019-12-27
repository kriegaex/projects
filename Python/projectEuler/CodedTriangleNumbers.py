import time
start = time.time()

def load_words_from_file(filename):
    f = open(filename, "r")
    file_content = f.read()
    f.close()
    wds = file_content.split(',')
    return wds


def gernerate_triangle():
    list = []
    for i in range(1, 20):
        triangle = 0.5 * i * (i + 1)
        list.append(triangle)
    return list
a = gernerate_triangle()

def istriangle(number):
    for x in a:
        if number == x:
            return True

def main():
    count = 0
    for i in load_words_from_file("p042_words.txt"):
        sum = 0
        x = i.strip("\"")
        for j in x:
            sum += (ord(j) - 64)
        if istriangle(sum):
            count += 1
    print (count)
    
main()

end = time.time()
print(end - start)

