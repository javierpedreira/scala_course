//sum function
def sum(f: Int => Int)(a: Int,b: Int): Int =
  if (a > b) 0  else f(a) + sum(f)(a +1, b)
// Write product like sum, that applies a function in an interval of numbers
def product(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b ) 1 else f(a) * product(f)(a + 1, b)

//write factorial in terms of product
def factorial(a: Int): Int = product(x => x)(1, a)

factorial(4)
sum(x => x)(1,2)

//write a generalization for product and sum
def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int,b: Int): Int =
  if (a > b) zero
  else combine(f(a), mapReduce(f, combine, zero)(a+1, b))

def product2(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x,y) => x * y, 1)(a,b)
def sum2(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x,y) => x + y, 0)(a,b)


product(x=>x)(1, 10)
product2(x=>x)(1, 10)
sum(x=>x)(2,5)
sum2(x=>x)(2,5)



