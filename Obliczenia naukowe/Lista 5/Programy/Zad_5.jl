#Obliczenia naukowe - Lista 5
#Bartosz Rzepkowski - 28.12.2015

push!(LOAD_PATH, "/home/bartas/Julia/Lista\ 5/Programy")
using systems

function matcond(n::Int, c::Float64)
# Function generates a random square matrix A of size n with
# a given condition number c.
# Inputs:
#	n: size of matrix A, n>1
#	c: condition of matrix A, c>= 1.0
#
# Usage: matcond (10, 100.0);
#
# Pawel Zielinski
        if n < 2
         error("size n should be > 1")
        end
        if c< 1.0
         error("condition number  c of a matrix  should be >= 1.0")
        end
        (U,S,V)=svd(rand(n,n))
        return U*diagm(linspace(1.0,c,n))*V'
end



n = 5
c = float64([1.0, 10.0, 10.0^3, 10.0^7, 10.0^12, 10.0^16])
for i=1:6
  x = ones(n)
  println("c = ", c[i])
  #Eliminacja Gaussa
  A = matcond(n, c[i])
  b = A * x
  A2 = copy(A)
  b2 = copy(b)
  x1 = Gauss(A, b, false)[1]
  #------------
  A = copy(A2)
  b = copy(b2)
  x2 = Gauss(A, b, true)[1]

  #Rozwiązanie dwuetapowe
  A = copy(A2)
  (lu1, ipvt1, err1) = rozkladLU(A, false)
  A = copy(A2)
  (lu2, ipvt2, err2) = rozkladLU(A, true)

  b = copy(b2)
  x3 = LUxb(lu1, false, b, ipvt1)
  b = copy(b2)
  x4 = LUxb(lu2, true, b, ipvt2)

  #********Błędy wzgledne macierzy*******
  println("x1 = ", norm(x1-x)/norm(x))
  println("x2 = ", norm(x2-x)/norm(x))
  println("x3 = ", norm(x3-x)/norm(x))
  println("x4 = ", norm(x4-x)/norm(x))
end

n = 10
c = float64([1.0, 10.0, 10.0^3, 10.0^7, 10.0^12, 10.0^16])
for i=1:6
  x = ones(n)
  println("c = ", c[i])
  #Eliminacja Gaussa
  A = matcond(n, c[i])
  b = A * x
  A2 = copy(A)
  b2 = copy(b)
  x1 = Gauss(A, b, false)[1]
  #------------
  A = copy(A2)
  b = copy(b2)
  x2 = Gauss(A, b, true)[1]

  #Rozwiązanie dwuetapowe
  A = copy(A2)
  (lu1, ipvt1, err1) = rozkladLU(A, false)
  A = copy(A2)
  (lu2, ipvt2, err2) = rozkladLU(A, true)

  b = copy(b2)
  x3 = LUxb(lu1, false, b, ipvt1)
  b = copy(b2)
  x4 = LUxb(lu2, true, b, ipvt2)

  #********Błędy wzgledne macierzy*******
  println("x1 = ", norm(x1-x)/norm(x))
  println("x2 = ", norm(x2-x)/norm(x))
  println("x3 = ", norm(x3-x)/norm(x))
  println("x4 = ", norm(x4-x)/norm(x))
end

n = 20
c = float64([1.0, 10.0, 10.0^3, 10.0^7, 10.0^12, 10.0^16])
for i=1:6
  x = ones(n)
  println("c = ", c[i])
  #Eliminacja Gaussa
  A = matcond(n, c[i])
  b = A * x
  A2 = copy(A)
  b2 = copy(b)
  x1 = Gauss(A, b, false)[1]
  #------------
  A = copy(A2)
  b = copy(b2)
  x2 = Gauss(A, b, true)[1]

  #Rozwiązanie dwuetapowe
  A = copy(A2)
  (lu1, ipvt1, err1) = rozkladLU(A, false)
  A = copy(A2)
  (lu2, ipvt2, err2) = rozkladLU(A, true)

  b = copy(b2)
  x3 = LUxb(lu1, false, b, ipvt1)
  b = copy(b2)
  x4 = LUxb(lu2, true, b, ipvt2)

  #********Błędy wzgledne macierzy*******
  println("x1 = ", norm(x1-x)/norm(x))
  println("x2 = ", norm(x2-x)/norm(x))
  println("x3 = ", norm(x3-x)/norm(x))
  println("x4 = ", norm(x4-x)/norm(x))
end

