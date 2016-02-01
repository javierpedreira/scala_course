def abs(x: Double) = if(x < 0) -x else x

def sqrt(x: Double) = {
  def sqrIter(guess: Double): Double = {
    if (isGoodEnough(guess)) guess
    else sqrIter(improve(guess, x)) }
  def improve(guess: Double, x: Double) = (guess + x / guess) / 2
  def isGoodEnough(guess: Double) = {
      if(abs(guess * guess - x) == 0) true
      else abs(guess * guess - x) / x < 0.001 }
  sqrIter(1.0)
  }

sqrt(2)
sqrt(4)
sqrt(16)
sqrt(0.001)
sqrt(0.1e-20)
sqrt(1.0e20)
sqrt(1.0e50)

