import re
import collections

f = open('p059_cipher.txt')

cipher = f.read().strip().split(',')
cipher = [int(x) for x in cipher]

# the key has three digits, so we slice the list with interval of 3
c1 = cipher[::3]
print(collections.Counter(c1))
c2 = cipher[1::3]
print(collections.Counter(c2))
c3 = cipher[2::3]
print(collections.Counter(c3))

firstKey = (71 ^ 32) #  71 appears the most times,    ^ means XOR,    32 is the ASCII of space, as space might appear the most times in a text

secondKey = (79 ^ 32)

thirdKey = (68 ^ 32)


plainText = ""
answear = 0
for i in range(len(cipher)):
    if i % 3 is 0:
        answear += (cipher[i] ^ firstKey)
        plainText += chr(cipher[i] ^ firstKey)
    elif i % 3 is 1:
        answear += (cipher[i] ^ secondKey)
        plainText += chr(cipher[i] ^ secondKey)
    elif i % 3 is 2:
        answear += (cipher[i] ^ thirdKey)
        plainText += chr(cipher[i] ^ thirdKey)
print(answear)
textFile = open("plainText.txt", 'w')
textFile.write(plainText)
textFile.close()
f.close()