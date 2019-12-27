Tally = [0]*6
In = int(input())
HobbyTitle = ['', 'Reading books', 'Play computer games', 'Sport', 'Programming', 'Watching TV']
while In != 0:
    Tally[In] += 1
    In = int(input())
for i in range(1, 6):
    print(HobbyTitle[i])
    print(Tally[i])

hobby = open('Tally.txt','w')
for i in range(1, 6):
    hobby.write(HobbyTitle[i] + '\n')
    hobby.write(str(Tally[i]) + '\n')
hobby.close

'''chaozy = open('Tally.txt','r')
for i in range(1,6):
    TextLine = chaozy.readline()
    digit = TextLine.strip('\n')
    Tally[i] = int(digit)
print(Tally)'''