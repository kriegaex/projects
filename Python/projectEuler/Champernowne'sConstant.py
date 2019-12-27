constant = ''
for i in range(1, 1000000):
    constant += str(i)

def tonumber(string):
    number = int(string)
    return number

product = tonumber(constant[1]) * tonumber(constant[10])

digit,product = 1, 1
while digit <= 1000000:
    product *= tonumber(constant[digit - 1])
    digit *= 10
print(product)