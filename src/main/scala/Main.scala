import Checkout.GetPriceUtils.{basket, getTotalPrice}

object Main {
  def main(args: Array[String]): Unit = {
    println(getTotalPrice(basket))
  }
}
