import string
file = open("names.txt","r")
s = file.read()
m = ""
for i in range(len(s)):
    if(s[i] != "\""):
        m += s[i]
l = m.split(',')
print(l)
l.sort()
dict = {}
alpha = string.ascii_uppercase
for i in range(len(alpha)):
    dict[alpha[i]] = i + 1 ##把alpha里的字母转换成list

score = 0
for i in range(len(l)):
    total = 0
    for j in range(len(l[i])):
        total += dict[l[i][j]]
    total *= (i+1)
    score += total
print (score)
