U₁ = [1 0 0 0 1 0 0 0;
      0 1 0 0 0 1 0 0;
      0 0 1 0 0 0 1 0;
      0 0 0 1 0 0 0 1;
      1 0 0 0 -1 0 0 0;
      0 1 0 0 0 -1 0 0;
      0 0 1 0 0 0 -1 0;
      0 0 0 1 0 0 0 -1]

U₂ = [1 0 0 0 0 0 0 0;
      0 1 0 0 0 0 0 0;
      0 0 1 0 0 0 0 0;
      0 0 0 1 0 0 0 0;
      0 0 0 0 1 0 0 0;
      0 0 0 0 0 1 0 0;
      0 0 0 0 0 0 e^(2*pi*im/4) 0;
      0 0 0 0 0 0 0 e^(2*pi*im/4)]

U₃ = [1 0 0 0 0 0 0 0;
      0 1 0 0 0 0 0 0;
      0 0 1 0 0 0 0 0;
      0 0 0 1 0 0 0 0;
      0 0 0 0 1 0 0 0;
      0 0 0 0 0 e^(2*pi*im/8) 0 0;
      0 0 0 0 0 0 1 0;
      0 0 0 0 0 0 0 e^(2*pi*im/8)]

U₄ = [1 0 1 0 0 0 0 0;
      0 1 0 1 0 0 0 0;
      1 0 -1 0 0 0 0 0;
      0 1 0 -1 0 0 0 0;
      0 0 0 0 1 0 1 0;
      0 0 0 0 0 1 0 1;
      0 0 0 0 1 0 -1 0;
      0 0 0 0 0 1 0 -1]

U₅ = [1 0 0 0 0 0 0 0;
      0 1 0 0 0 0 0 0;
      0 0 1 0 0 0 0 0;
      0 0 0 e^(2*pi*im/4) 0 0 0 0;
      0 0 0 0 1 0 0 0;
      0 0 0 0 0 1 0 0;
      0 0 0 0 0 0 1 0;
      0 0 0 0 0 0 0 e^(2*pi*im/4)]

U₆ = [1 1 0 0 0 0 0 0;
      1 -1 0 0 0 0 0 0;
      0 0 1 1 0 0 0 0;
      0 0 1 -1 0 0 0 0;
      0 0 0 0 1 1 0 0;
      0 0 0 0 1 -1 0 0;
      0 0 0 0 0 0 1 1;
      0 0 0 0 0 0 1 -1]

U₇ = [1 0 0 0 0 0 0 0;
      0 0 0 0 1 0 0 0;
      0 0 1 0 0 0 0 0;
      0 0 0 0 0 0 1 0;
      0 1 0 0 0 0 0 0;
      0 0 0 0 0 1 0 0;
      0 0 0 1 0 0 0 0;
      0 0 0 0 0 0 0 1]

v100 = [0; 0; 0; 0; 1; 0; 0; 0]
v001 = [0; 1; 0; 0; 0; 0; 0; 0]
v110 = [0; 0; 0; 0; 0; 0; 1; 0]
v011 = [0; 0; 0; 1; 0; 0; 0; 0]

# println(U₇*v001)
# println(U₁*U₂*U₃*U₄*U₅*U₆*U₇)
