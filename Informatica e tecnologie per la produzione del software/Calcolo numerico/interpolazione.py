# -*- coding: utf-8 -*-
"""
@author: marco

INTERPOLAZIONE
"""

import numpy as np
import matplotlib.pylab as mplt



def fattorizzazione_LU_piv_parziale(MATRICE): 
   U = np.copy(MATRICE)
   U = np.asmatrix(U)
   L = np.eye(len(MATRICE))
   L = np.asmatrix(L)
   P = np.eye(len(MATRICE))
   for j in range (0, len(U)):    
       #trovo il pivot considerando una sottomatrice di U
       #np.unravel_index mi restituisce le coordinate del max
       iMax,jMax = np.unravel_index(np.abs(U[j:,j]).argmax(), U[j:,j].shape)
    
       
       #jMax e iMax sono le coordinate del max nella sottomatrice U[j:, j:]. Sommo j ad entrambi per trovare le coordinate della matrice U
       jMax += j
       iMax += j
       #posiziono il pivot nella posizione corretta         
       if iMax != j or jMax != j:
           #aggiorno L lavorando solo su una parte della matrice
           L[[j, iMax], :j] = L[[iMax,j], :j]
 
           #aggiorno U
           U[[j, iMax], :] = U[[iMax, j], :]
           
           #aggiorno P
           P[[j, iMax], :] = P[[iMax, j], :]
           
       Mn = np.asmatrix(np.identity(len(MATRICE)))
       ln = np.copy(U[j+1:, j]/U[j,j])
       Mn[j+1:, j] = -U[j+1:, j]
       U[j+1:, j] = 0
       U[j+1:, j+1:] = U[j+1:, j+1:] - ln.dot(U[j, j+1:])
       L[j+1:,j] = np.copy(ln)
   return P,L,U


def algoritmo_sostituzione_in_avanti(L, b):
    y = np.empty(len(b))
    y[0] = b[0]/L[0,0]
    for i in range (1, len(L)):
        y[i] = (b[i] - np.dot(L[i, 0:i], y[0:i]))/ L[i,i]
    return y
 
       
def algoritmo_sostituzione_indietro(U, y):
    x = np.zeros(len(y))
    dim = len(x)
    x[dim-1] = y[dim-1] / U[dim-1, dim-1]
    for i in range (dim-2, -1, -1): #Da dim-2 perchè l'ultimo elemento (in posizione dim-1) l'ho già calcolato, -1 perchè devo arrivare alla posizione 0 
        x[i] = (y[i] - np.dot(U[i, i:dim], x[i:dim])) / U[i,i]

    return x


def ris_sistema_LU_piv_parz(A, b):
    P, L, U = fattorizzazione_LU_piv_parziale(A)
   
    y = algoritmo_sostituzione_in_avanti(L, P.dot(b.T))
    
    x = algoritmo_sostituzione_indietro(U, y)
    
    return x





def matrice_Vandermonde(x):
    V = np.zeros(shape = (len(x),len(x)))
  
    
    
    for i in range (0, len(x)):
        for j in range(0, len(x)):
            V[i,j] = x[i]**j
            
    
    
    return V




def pol_lagrange(x, y, punti):
    l = np.zeros(len(punti))
    
    for k in range(0, len(punti)):
        somma = 0        #p(xi)
        for i in range(0, len(x)):
            term = y[i]  #ai
            for j in range(0, len(x)):
                if i != j:
                    term *= (punti[k] - x[j])/(x[i]-x[j])   #lj(x): produttoria di (x-xi)/(xj-xi)
            somma += term   
        l[k] = somma  #ordinata del punto
    return l


############################################################ 


f = (lambda x: 5*x**3 + 3*x**2 + 10*x -3)
    
x = np.array([1,2,3,4,5])
y = np.array([15,69,189,405,747])
    
a = 1
b = 9
punti = np.linspace(a,b, 300)


'''
#base delle potenze
V = matrice_Vandermonde(x)

a = ris_sistema_LU_piv_parz(V, y)

p = np.poly1d(np.flip(a,0))


err_ass_pot = np.max(np.abs(p(punti) - f(punti)))
err_rel_pot = np.max(np.abs(p(punti) - f(punti))/np.abs(f(punti)))
print("Base delle potenze:")
print("\nErrore assoluto in norma infinito con la base delle potenze:\n",err_ass_pot)
print("\n\nErrore relativo in norma infinito con la base delle potenze:\n",err_rel_pot)


valoriPot = p(punti)
mplt.plot(punti, 0*punti, punti, valoriPot, 'yo',  x,y, 'bo', )
mplt.show()
'''

'''
#base di Lagrange
valori_lag = pol_lagrange(x, y, punti)
print("Base di Lagrange:")
err_ass_lag = np.max(np.abs(valori_lag - f(punti)))
err_rel_lag = np.max(np.abs(valori_lag - f(punti))/np.abs(f(punti)))
print("\nErrore assoluto in norma infinito con la base di Lagrange:\n",err_ass_lag)
print("\n\nErrore relativo in norma infinito con la base di Lagrange:\n",err_rel_lag)

mplt.plot(x, 0*x, punti, valori_lag, 'o', x,y, x, y, 'o')
mplt.show()
'''

