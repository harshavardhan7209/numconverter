import math
def decimal_to_other(number, base_to_convert):
    z = ''
    ya = {10: 'A', 11: 'B', 12:'C',13:'D',14:'E',15:'F'}
    while number/base_to_convert!=0:
        x = str(number%base_to_convert)
        if base_to_convert==16:
            if ya.get(int(x))!=None:
                x = ya.get(int(x)) 
        z = z+(x)
        number=int(number/base_to_convert)
    return z[::-1]
def other_to_decimal(num, converter):
    lenofnum = len(str(num))-1
    za = 0
    z=0
    ya = {'a': 10, 'A':10,'B':11,'b':11,'C':12,'c':12,'D':13,'d':13,'E':14,'e':14,'F':15,'f':15}
    while lenofnum!=-1:
        if converter==16:
            isint=True
            try:
                int(str(num)[za])
            except ValueError:
                isint=False
            if isint==False:
                if ya.get(str(num)[za]) !=None:
                    z+=(int(str(ya.get(str(num)[za])))*int(math.pow(converter, lenofnum)))
                else:
                    print("Not a valid Hexadecimal number.")
                    exit()
            else:
                z+=(int(str(num)[za])*int(math.pow(converter, lenofnum)))
        else:
            z+=(int(str(num)[za])*int(math.pow(converter, lenofnum)))
        za+=1
        lenofnum-=1
    return z
    
def main():
    zrz = 0
    hexalpha = "abcdef0123456789"
    num = input("Enter a number: ")
    base = int(input("Enter base of number(2,8,10,16): "))
    x=len(num)
    z=0
    isf = True
    isHex = False
    isint = True
    if base==2 or base==8 or base==10 or base==16:
        for i in range(len(num)):
            try:
                int(num[i])
            except ValueError:
                isint = False
            if isint==True:
                z+=int(num[i])
            try:
                hexalpha.index(num[i])
            except ValueError:
                isHex = False
                isf = False
            if num[i] == "0":
                zrz+=1
            if isf==True:
                isHex = True
            else:
                isHex = False
                break
    x = x-zrz
    if base==2 and (z/x)<=1 and isint==True:
        print("Octal Digit is: ("+str(decimal_to_other(other_to_decimal(num, 2), 8))+")8")
        print("Decimal digit is: ("+str(other_to_decimal(num, 2))+")10")
        print("Hexadecimal digit is: ("+str(decimal_to_other(other_to_decimal(num, 2), 16))+")16")
    elif base==8 and int(z/len(num))<=7 and isint==True:
        print("Binary Digit is: ("+str(decimal_to_other(other_to_decimal(num,8),2))+")2")
        print("Decimal Digit is: ("+str(other_to_decimal(num, 8))+")10")
        print("Hexadecimal Digit is: ("+str(decimal_to_other(other_to_decimal(num, 8), 16))+")16")
    elif base==10 and z/x<=9 and isint==True:
        print("Binary digit is: (" + str(decimal_to_other(int(num), 2))+")2")
        print("Octal digit is: (" + str(decimal_to_other(int(num), 8))+")8")
        print("Hexadecimal digit is: (" + str(decimal_to_other(int(num), 16))+")16")
    elif base==16 and isHex==True:
        print("Binary digit is: ("+str(decimal_to_other(other_to_decimal(num, 16), 2))+")2")
        print("Octal digit is: ("+str(decimal_to_other(other_to_decimal(num, 16), 8))+")8")
        print("Decimal digit is: ("+str(other_to_decimal(num,16))+")10")
    else:
        print("Not a valid base or number or both.")
        if base!=2 and base!= 8 and base!=10 and base!=16:
            idk = "base"
        else:
            idk = "number"
        x = "Do you want to see results for your invalid " + idk + "?: "
        stir = input(x)
        if stir[0].lower()=="y":
            print("\nThen Here you go: \n")
            print("Binary digit: " + str(decimal_to_other(other_to_decimal(num, base), 2)))
            print("Octal digit: " + str(decimal_to_other(other_to_decimal(num, base), 8)))
            print("Decimal digit: " + str(other_to_decimal(num, base)))
            print("Hexadecimal digit: " + str(decimal_to_other(other_to_decimal(num, base), 16)))
        else: 
            print("Ok, bye :(")
main()
