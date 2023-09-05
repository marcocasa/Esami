# -*- coding: utf-8 -*-
"""

@author: marco

SISTEMI LINEARI
"""


import numpy as np


    

def fattorizzazione_LU_no_piv(MATRICE):     
    U = np.copy(MATRICE)
    U = np.asmatrix(U)
    L = np.identity(len(MATRICE))
    L = np.asmatrix(L)
    passo = 1
    for j in range(0, len(MATRICE)):
       ln = np.copy(U[j+1:, j]/U[j,j])
       U[j+1:, j] = 0
       U[j+1:, j+1:] = U[j+1:, j+1:] - ln.dot(U[j, j+1:])
       L[j+1:,j] = np.copy(ln)
       
       print("PASSO", passo) 
       print("Matrice L:")
       print(L, "\n")
       print("Matrice U:")
       print(U, "\n\n")
       passo += 1
       
    return L,U
    


def fattorizzazione_LU_piv_parziale(MATRICE): 
   print("Matrice iniziale:\n", MATRICE, "\n\n")
   U = np.copy(MATRICE)
   U = np.asmatrix(U)
   L = np.eye(len(MATRICE))  
   L = np.asmatrix(L)
   P = np.eye(len(MATRICE))
   passo = 1
   for j in range (0, len(U)):    
       #trovo il pivot considerando una sottomatrice di U
       #np.unravel_index mi restituisce le coordinate del max
       iMax = np.abs(U[j:,j]).argmax()
    
       iMax += j
       #posiziono il pivot nella posizione corretta         
       if iMax != j:
           #aggiorno L lavorando solo su una parte della matrice
           L[[j, iMax], :j] = L[[iMax,j], :j]
 
           #aggiorno U
           U[[j, iMax], :] = U[[iMax, j], :]
           
           #aggiorno P
           P[[j, iMax], :] = P[[iMax, j], :]
           
       ln = np.copy(U[j+1:, j]/U[j,j])
       U[j+1:, j] = 0
       U[j+1:, j+1:] = U[j+1:, j+1:] - ln.dot(U[j, j+1:])
       L[j+1:,j] = np.copy(ln)
       
       print("PASSO", passo) 
       print("Matrice P:")
       print(P, "\n")
       print("Matrice L:")
       print(L, "\n")
       print("Matrice U:")
       print(U, "\n\n")
      
       
       passo+=1
   return P,L,U
        
    
    
def fattorizzazione_LU_piv_totale(MATRICE): 
   print("Matrice iniziale:\n\n", MATRICE)
   U = np.copy(MATRICE)
   U = np.asmatrix(U) 
   L = np.eye(len(MATRICE)) 
   L = np.asmatrix(L)
   P = np.eye(len(MATRICE))
   Q = np.eye(len(MATRICE))
   passo = 1
   for j in range (0, len(U)):    
       #trovo il pivot considerando una sottomatrice di U
       #np.unravel_index mi restituisce le coordinate in due dimensioni del max
       iMax,jMax = np.unravel_index(np.abs(U[j:,j:]).argmax(), U[j:,j:].shape)
       
       #jMax e iMax sono le coordinate del max nella sottomatrice U[j:, j:]. Sommo j ad entrambi per trovare le coordinate nella matrice U
       jMax += j
       iMax += j
       #posiziono il pivot nella posizione corretta         
       if iMax != j or jMax != j:
           #aggiorno L lavorando solo su una parte della matrice
           L[[j, iMax], :j] = L[[iMax,j], :j]
           
           #aggiorno U
               #scambio le righe
           U[[j, iMax], :] = U[[iMax, j], :]
           
               #scambio le colonne
           U[:, [j, jMax]] = U[:, [jMax, j]]
           
           #aggiorno P
           P[[j, iMax], :] = P[[iMax, j], :]
          
           #aggiorno Q
           Q[:, [j, jMax]] = Q[:, [jMax, j]]
        
           
       ln = np.copy(U[j+1:, j]/U[j,j])
       U[j+1:, j] = 0
       U[j+1:, j+1:] = U[j+1:, j+1:] - ln.dot(U[j, j+1:])
       L[j+1:,j] = np.copy(ln)
      
       print("PASSO", passo)
       print("Matrice P:")
       print(P, "\n")  
       print("Matrice Q:")
       print(Q, "\n")
       print("Matrice L:")
       print(L, "\n")
       print("Matrice U:")
       print(U, "\n\n")
      
       passo+=1
           
   return P,Q,L,U
        

def algoritmo_sostituzione_in_avanti(L, b):
    y = np.zeros(len(b))
    
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

def ris_sistema_LU_no_piv(A, b):
    L, U = fattorizzazione_LU_no_piv(A)
    
    
    y = algoritmo_sostituzione_in_avanti(L, b)
    
    x = algoritmo_sostituzione_indietro(U, y)
   
    return x




def ris_sistema_LU_piv_parz(A, b):
    P, L, U = fattorizzazione_LU_piv_parziale(A)
   
    y = algoritmo_sostituzione_in_avanti(L, P.dot(b.T))
    
    x = algoritmo_sostituzione_indietro(U, y)
    
    return x

def ris_sistema_LU_piv_tot(A, b):
    P, Q, L, U = fattorizzazione_LU_piv_totale(A)
   
    y = algoritmo_sostituzione_in_avanti(L, P.dot(b.T))
    
    z = algoritmo_sostituzione_indietro(U, y)
    
    return Q.dot(z)

############################################################ 
    

#A = np.asmatrix([[0.,3.,-4.,2.],[2.,5.,3.,1.], [9.,-3.,-2.,3.], [4.,-1.,-3.,7.]])
A = np.asmatrix([[1.,3.,-4.,2.],[2.,5.,3.,1.], [9.,-3.,-2.,3.], [4.,-1.,-3.,7.]])
b = np.array([1., 3., -4., 3])


#Fattorizzazione LU e risoluzione sistema lineare senza pivoting
L, U = fattorizzazione_LU_no_piv(A)
'''
print("\n\nSoluzione:\n")
print("Matrice L:\n", L) 
print("\nMatrice U:\n", U)  
print("\nMatrice A-LU:\n", A-np.dot(L,U))
x = ris_sistema_LU_no_piv(A, b)
print("\n\nSoluzione del sistema:\n",x)
'''
print("\n", np.linalg.cond(L))
print("\n",np.linalg.cond(U))
print("\n",np.linalg.cond(A))


#Fattorizzazione LU e risoluzione sistema lineare con pivoting parziale
P, L, U = fattorizzazione_LU_piv_parziale(A)
'''
print("\n\nSoluzione:\n")
print("\n\nP:\n", P)
print("\n\nL:\n", L)
print("\n\nU:\n", U) 
print("\nMatrice PA-LU:\n", np.dot(P,A)-np.dot(L,U))
x = ris_sistema_LU_piv_parz(A, b)
print("\n\nSoluzione del sistema:\n",x)
'''
print("\n", np.linalg.cond(L))
print("\n",np.linalg.cond(U))
print("\n",np.linalg.cond(A))



#Fattorizzazione LU e risoluzione sistema lineare con pivoting totale
P, Q, L, U = fattorizzazione_LU_piv_totale(A)
'''
print("\n\nSoluzione:\n")
print("P:\n", P)
print("\n\nQ:\n", Q)
print("\n\nL:\n", L)
print("\n\nU:\n", U)   
print("\nMatrice PAQ-LU:\n", np.dot(np.dot(P,A), Q)-np.dot(L,U))
x = ris_sistema_LU_piv_tot(A, b)
print("\n\nSoluzione del sistema:\n",x)
'''
print("\n", np.linalg.cond(L))
print("\n",np.linalg.cond(U))
print("\n",np.linalg.cond(A))
