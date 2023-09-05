
"""

@author: marco

ZERI DI FUNZIONE
"""

import numpy as np
import matplotlib.pylab as mplt

def retta_pass_2_punti(x1, y1, x2, y2):
    return (lambda x: (x*(y1-y2)/(x1-x2)) + y1 - x1*(y1-y2)/(x1-x2))

def errore_misto(x, atol, rtol, f):
    return np.abs(f(x)) / (atol/rtol + np.abs(x))

def met_bisezione(a, b, atol, rtol, f, x):
    if(f(a) == 0):
        c = a
    else:
        if(f(b) == 0):
            c = b
        else:
            if(f(a) * f(b) < 0):
                passo = 1
                c = (a + b)/2
                errore = errore_misto(c, atol, rtol, f)
                print("\nPasso:", passo)
                print("a:", a, "\nb:", b, "\nc:", c)
                print("atol:", atol)
                print("rtol:", rtol)
                print("errore:", errore)
                mplt.plot(x, f(x), x, 0*x, a, f(a), 'o', b, f(b), 'o', c, f(c), 'o') #creo il grafico
                mplt.show()
                
                while errore > rtol:
                    passo += 1
                    if (f(a)*f(c)) < 0:
                        #a quella precedente
                        b = c
                    else:
                        #b quella precedente
                        if (f(c)*f(b)) < 0:
                            a = c
                    c = (a +     b)/2
                    errore = errore_misto(c, atol, rtol,f)
                    print("\nPasso:", passo)
                    print("a:", a, "\nb:", b, "\nc:", c)
                    print("rtol:", rtol)
                    print("errore:", errore)
                    mplt.plot(x, f(x), x, 0*x, a, f(a), 'o', b, f(b), 'o', c, f(c), 'o') #creo il grafico
                    mplt.show()
            else:
                c = None
    return c

def met_newton(x0, f, fder, x, atol, rtol):
        if(fder(x0) == 0):
            xsucc = None
        else:
            passo = 0
            
            errore = errore_misto(x0, atol, rtol, f)
            print("\nPasso:", passo)
            print("x 0 :", x0)
            print("atol:", atol)
            print("rtol:", rtol)
            print("errore:", errore)
            xsucc = x0
            mplt.plot(x, f(x), x, 0*x, xsucc, f(xsucc), 'o') #creo il grafico con la funzione, la retta passante per i due punti e i due punti
            mplt.show()
            while errore > rtol:
                passo += 1
                xprec = xsucc
                xsucc = xprec - f(xprec)/fder(xprec)
                errore = errore_misto(xsucc, atol, rtol,f)
                print("\nPasso:", passo)
                print("x",passo-1,":", xprec)
                print("x",passo,":", xsucc)
                print("errore:", errore)
                retta = retta_pass_2_punti(xprec,f(xprec),xsucc, 0)  #mi calcolo la retta passante per i punti xn e xn+1
                mplt.plot(x, f(x), x, 0*x, x, retta(x), xprec, f(xprec), 'o', xsucc, f(xsucc), 'o') #creo il grafico con la funzione, la retta passante per i due punti e i due punti
                mplt.show()
                
        return xsucc
    
def met_dir_costante(x0, f, df, x, atol, rtol): 
        if(df == 0):
            xsucc = None
        else:
            passo = 0
            errore = errore_misto(x0, atol, rtol, f)
            print("\nPasso:", passo)
            print("x 0 :", x0)
            print("atol:", atol)
            print("rtol:", rtol)
            print("errore:", errore)
            xsucc = x0
            mplt.plot(x, f(x), x, 0*x, xsucc, f(xsucc), 'o') #creo il grafico con la funzione, la retta passante per i due punti e i due punti
            mplt.show()
            while errore > rtol:
                passo += 1
                xprec = xsucc
                xsucc = xprec - f(xprec)/df
                errore = errore_misto(xsucc, atol, rtol,f)
                print("\nPasso:", passo)
                print("x",passo-1,":", xprec)
                print("x",passo,":", xsucc)
                print("rtol:", rtol)
                print("errore:", errore)
                retta = retta_pass_2_punti(xprec,f(xprec),xsucc, 0)  #mi calcolo la retta passante per i punti xn e xn+1
                mplt.plot(x, f(x), x, 0*x, x, retta(x), xprec, f(xprec), 'o', xsucc, f(xsucc), 'o') #creo il grafico con la funzione, la retta passante per i due punti e i due punti
                mplt.show()
                
        return xsucc

def met_falsa_posizione(x0, x1, f, x, atol, rtol):
            if((x1-x0) == 0):
                xsucc = None
            else:
                passo = 0
                errore = errore_misto(x0, atol, rtol, f)
                print("\nPasso:", passo)
                print("x 0 :", x0)
                print("x 1 :", x1)
                print("atol:", atol)
                print("rtol:", rtol)
                print("errore:", errore)
                retta = retta_pass_2_punti(x0,f(x0),x1, f(x1))  #mi calcolo la retta passante per i punti xn e xn+1
                mplt.plot(x, f(x), x, 0*x, x, retta(x), x0, f(x0), 'o', x1, f(x1), 'o') #creo il grafico con la funzione, la retta passante per i due punti e i due punti
                mplt.show()
                xsucc = x1
            
                while errore > rtol:
                    passo += 1
                    xprec = xsucc
                    xsucc = xprec - f(xprec)/((f(xprec)-f(x0))/(xprec-x0))
                    errore = errore_misto(xsucc, atol, rtol,f)
                    print("\nPasso:", passo)
                    print("x 0:", x0)
                    print("x",passo-1,":", xprec)
                    print("x",passo,":", xsucc)
                    print("errore:", errore)
                    retta2 = retta_pass_2_punti(x0,f(x0),xsucc, f(xsucc))  #mi calcolo la retta passante per i punti xn e xn+1
                    mplt.plot(x, f(x), x, 0*x, x, retta2(x), x0, f(x0), 'o', xsucc, f(xsucc), 'o') #creo il grafico con la funzione, la retta passante per i due punti e i due punti
                    mplt.show()
    
                
            return xsucc



def met_secanti(x0, x1, f, punti, atol, rtol): 
            if((x1-x0) == 0):
                xsucc = None
            else:
                passo = 0
                errore = errore_misto(x0, atol, rtol, f)
                print("\nPasso:", passo)
                print("x 0:", x0)
                print("x 1:", x1)
                print("atol:", atol)
                print("rtol:", rtol)
                print("errore:", errore)
                retta = retta_pass_2_punti(x0,f(x0),x1, f(x1))  #mi calcolo la retta passante per i punti xn e xn+1
                mplt.plot(punti, f(punti), punti, 0*punti, punti, retta(punti), x0, f(x0), 'o', x1, f(x1), 'o') #creo il grafico con la funzione, la retta passante per i due punti e i due punti
                mplt.show()
                xsucc = x1 
                x = x0
                while errore > rtol:
                    passo += 1
                    xprec = x #Xn-1
                    x = xsucc #Xn
                    xsucc = x - f(x)/((f(x)-f(xprec))/(x-xprec)) #Xn+1
                    errore = errore_misto(xsucc, atol, rtol,f)
                    print("\nPasso:", passo)
                    print("x",passo-1,":", xprec),
                    print("x",passo,":", x)
                    print("x",passo+1,":", xsucc)
                    print("errore:", errore)
                    retta2 = retta_pass_2_punti(x,f(x),xsucc, f(xsucc))  #mi calcolo la retta passante per i punti xn e xn+1
                    mplt.plot(punti, f(punti), punti, 0*punti, punti, retta2(punti), x, f(x), 'o', xsucc, f(xsucc), 'o') #creo il grafico con la funzione, la retta passante per i due punti e i due punti
                    mplt.show()
            
                
            return xsucc



def met_Brent(a, b, f, punti, atol, rtol): 
            if((b-a) == 0):
                c = None
            else:
                xprec = a
                x = b
                k = 1
                errore = errore_misto(x, atol, rtol, f)
                print("\nPasso:", 0)
                print("a", k,":", a)
                print("b", k,":", b)
                print("x", k-1,":", xprec)
                print("x", k,":", x)
                print("atol:", atol)
                print("rtol:", rtol)
                print("errore:", errore)
                
                flagMet = False
                while errore > rtol:
                    c = x - f(x)/((f(x) - f(xprec))/(x - xprec)) 
                    print("\nPasso:", k)
                    if c < a or c > b:
                        flagMet = True
                        c = (a+b) / 2
                        if(f(a) * f(c) < 0):
                            b = c
                            x = c
                            xprec = a
                            print("c si trova nella prima metà")
                        else :
                            if f(c) * f(b) < 0:
                                a = c
                                x = c
                                xprec = b
                                print("c si trova nella seconda metà")
                    else:
                        x = c
                        if f(a) * f(c) < 0:
                            b = c
                            print("c si trova nella prima metà")
                        else:
                            if f(c) * f(b) < 0:
                                a = c
                                print("c si trova nella seconda metà")
                    print("c:" , c)
                    errore = errore_misto(c, atol, rtol, f)
                    print("a", k,":", a)
                    print("b", k,":", b)
                    print("x", k-1,":", xprec)
                    print("x", k,":", x)
                    print("atol:", atol)
                    print("rtol:", rtol)
                    print("errore:", errore)
                    
                    if flagMet == True:
                        print("è stato usato il metodo di bisezione")
                    else:
                        print("è stato usato il metodo delle secanti")
                        
                    retta = retta_pass_2_punti(a,f(a),b, f(b))  #mi calcolo la retta passante per i punti xn e xn+1
                    mplt.plot(punti, f(punti), punti, 0*punti, punti, retta(punti), a, f(a), 'ro' , b, f(b), 'o', c, f(c), 'k*') #ro: pallino color rosso. k*: asterisco color nero
                    mplt.show()
                    flagMet = False
                    k+=1
                    
            return c
############################################################                           
                        
                
                



a = -4
b = 2

x0 = -2

numPunti = 500
f = (lambda x: x**2 - 5*x - 10)
fder = (lambda x: 2*x - 5)
atol = 0.003
rtol = 0.002
x = np.linspace(-7, 7, numPunti) #ritorna un array con numPunti punti tra a e b






'''
#Metodo delle bisezioni

c = met_bisezione(a, b, atol, rtol, f, x)

if(c == None):
    print("La funzione non ha zeri")
else:
    print("Lo zero è:", c)
'''

'''
#Metodo di Newton

c = met_newton(x0, f, fder, x, atol, rtol)
if(c == None):
    print("Non è possibile usare il metodo di newton")
else:
    print("Lo zero è:", c)
'''
'''
#Metodo della direzione costante
df = -9
c = met_dir_costante(x0, f, df, x, atol, rtol)
if(c == None):
    print("Non è possibile usare il metodo della direzione costante")
else:
    print("Lo zero è:", c)
'''

'''
#Metodo della falsa posizione
c = met_falsa_posizione(a, b, f, x, atol, rtol)
if(c == None):
    print("Non è possibile usare il metodo di della falsa posizione")
else:
    print("Lo zero è:", c)
'''

'''
#Metodo delle secanti
c = met_secanti(a, b, f, x, atol, rtol)
if(c == None):
    print("Non è possibile usare il metodo di della delle secanti")
else:
    print("Lo zero è:", c)
'''

'''
#Metodo di Brent
c = met_Brent(a, b, f, x, atol, rtol)
if(c == None):
    print("Non è possibile usare il metodo di brent")
else:
    print("Lo zero è:", c)
'''