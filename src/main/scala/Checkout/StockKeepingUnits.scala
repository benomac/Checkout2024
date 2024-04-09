package Checkout

import Checkout.GetPriceUtils.{basket, getTotalPrice}

object StockKeepingUnits {

  case class Special(quantity: Int, SpecialPrice: Double)

  case class SKU(price: Double, special: Option[Special])

  def main(args: Array[String]): Unit = {
    println(getTotalPrice(basket))
  }

}


//  def mapBasketAndRemoveNonItems(basket: List[Char]): Map[Char, Int] =
//    basket.filterNot(c => skus.contains(c)).foreach(n => println(s"item $n not recognised"))
//    basket.filter(c => skus.contains(c))
//      .groupBy(identity)
//      .map(x => x._1 -> x._2.size)
//
//  def getSpecialPrice(sku: SKU, special: Special, quantity: Int): Double =
//    if (quantity < special.quantity)
//      sku.price * quantity
//    else
//      ((quantity / special.quantity) * special.SpecialPrice) + quantity % special.quantity * sku.price
//
//  def getPricePerItem(sku: SKU, quantity: Int): Double =
//    sku.special match
//      case Some(special) => getSpecialPrice(sku, special, quantity)
//      case None => sku.price * quantity
//
//  def getTotalPrice(basketMapped: List[Char]): Double =
//    mapBasketAndRemoveNonItems(basketMapped)
//      .map(x => getPricePerItemFunc(skus(x._1), x._2))
//      .sum