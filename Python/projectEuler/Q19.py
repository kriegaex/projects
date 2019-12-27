def leap(year):
    leap = False
    if (year % 4 == 0 and year % 100 != 0) or (year % 100 == 0 and year % 100 == 0):
        leap = True
    return leap

def Sunday(day):
    sunday = False
    if day % 7 == 0:
        sunday = True
    return sunday

year = 1901
day = 1
total = 0
while year <= 2000:
    for month in range(1, 13):
        if Sunday(day):
            total += 1
        if month == 4 or 6 or 9 or 11 :
                day += 30
        if month == 1 or 3 or 5 or 7 or 8 or 10 or 12:
                day += 31
        if month == 2:
            if leap(year):
                day += 29
            else:
                day += 28
    year += 1
print(total)
