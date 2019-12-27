N = 600851475143 
Flag,i = True,2
while Flag == True:
    if N < i^2:
        print(N);Flag = False
    else:
        while N % i == 0:
            N = N / i
            if N < i^2:
                print(N);Flag = True;break
    i += 1
    
                
        
