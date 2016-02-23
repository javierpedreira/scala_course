class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")
  def numer = x / gcd(x, y)
  def denom = y / gcd(x, y)
  def this(x: Int) = this(x, 1)

  def < (that: Rational) = numer * that.denom < that.numer * denom
  def max(that: Rational) = if (this < that) that else this
  def + (that: Rational) =
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def unary_- : Rational = new Rational(-numer, denom)
  def - (that: Rational) = this + -that
  private def gcd(a: Int,b: Int): Int = if(b == 0) a else gcd(b, a% b)


  override def toString = numer + "/" + denom
}


val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
x - y - z


