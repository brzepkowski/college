#Obliczenia naukowe - Lista 2
#Bartosz Rzepkowski - 8.11.2015r.
using Gadfly

function recurse(n,c, x0)
  if n == 0 return x0
  else
    x = recurse(n-1, c, x0)
    return (x^2 + c)
  end
end

A = zeros(40)

# 1)
for i = 1:40
  A[i] = recurse(i, -2, 1)
end
plot(x = 1:40, y = A, Geom.point, Geom.line)
println(A)

# 2)
for i = 1:40
  A[i] = recurse(i, -2, 2)
end
println(A)
# 3)
for i = 1:40
  A[i] = recurse(i, -2, 1.99999999999999)
end
println(A)
plot(x = 1:40, y = A, Geom.point, Geom.line)

# 4)
for i = 1:40
  A[i] = recurse(i, -1, 1)
end
println(A)
plot(x = 1:40, y = A, Geom.point, Geom.line)
# 5)
for i = 1:40
  A[i] = recurse(i, -1, -1)
end
println(A)
plot(x = 1:40, y = A, Geom.point, Geom.line)
# 6)
for i = 1:40
  A[i] = recurse(i, -1, 0.75)
end
println(A)
plot(x = 1:40, y = A, Geom.point, Geom.line)

# 7)
for i = 1:40
  A[i] = recurse(i, -1, 0.25)
end
println(A)
plot(x = 1:40, y = A, Geom.point, Geom.line)
