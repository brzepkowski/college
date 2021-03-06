#Obliczenia naukowe - Lista 2
#Bartosz Rzepkowski - 8.11.2015r.
# 1)  p0 = 0.01, r = 3
A = zeros(41)
A[1] = 0.01

function recurse(n)
  if n == 0 return A[1]
  elseif A[n+1] != 0 return A[n+1]
  else
    x = recurse(n-1)
    A[n+1] = (x + 3*x*(1-x))
    return A[n+1]
  end
end

for i = 0:40
  recurse(i)
  println(A[i+1])
end


A = zeros(41)
A[1] = 0.01
for i=0:10
  recurse(i)
  println(A[i+1])
end

a = A[11] * 1000
A[11] = trunc(a)/1000
println("Obcięcie!!")
for i=11:40
  recurse(i)
  println(A[i+1])
end

# 2)
function recurse_f32(n)
  if n == 0 return float32(0.01)
  else
    x = float32(recurse_f32(n-1))
    return float32((x + 3*x*(1-x)))
  end
end

function recurse_f64(n)
  if n == 0 return float64(0.01)
  else
    x = float64(recurse_f64(n-1))
    return float64((x + 3*x*(1-x)))
  end
end

for i = 1:40
  println("F32 = ", recurse_f32(i), " F64 = ", recurse_f64(i))
end
