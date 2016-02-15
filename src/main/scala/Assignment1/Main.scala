package Assignment1

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if(c == r || c == 0) 1
    else pascal(c - 1, r -1) + pascal(c, r -1)
  }

  /**
    * Exercise 2 Propperly parenthesized strings
    */
  def balance(chars: List[Char]): Boolean = {
    def countOpens(numOpens: Int, chars: List[Char]):Boolean =  {
      if(chars.isEmpty)
        numOpens == 0
      else {
        val n =
          if (chars.head == '(') numOpens + 1
          else if (chars.head == ')') numOpens - 1
          else numOpens
        if (n >= 0) countOpens(n, chars.tail)
        else false
      }
    }
    countOpens(0, chars)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = ???
}
