package Checkout

import Checkout.StockKeepingUnits.{SKU, Special}

object GetPriceUtils {

  val skus: Map[Char, SKU] = Map(
    'a' -> SKU(0.50, Some(Special(3, 1.30))),
    'b' -> SKU(0.30, Some(Special(2, 0.45))),
    'c' -> SKU(0.20, None),
    'd' -> SKU(0.15, None)
  )

  val basket = List('a', 'a', 'a', 'h', 'b', 'a', 'b', 'j', 'c')

  val mapBasketAndRemoveNonItems: (List[Char], Map[Char, SKU]) => Map[Char, Int] = { (basket, skus) =>
    basket.filterNot(c => skus.contains(c)).foreach(n => println(s"Item $n not recognised"))
    basket.filter(c => skus.contains(c))
      .groupBy(identity)
      .map(x => x._1 -> x._2.size)
  }

  val getSpecialPrice: (SKU, Special, Int) => Double = { (sku, special, quantity) =>
    if (quantity < special.quantity)
      sku.price * quantity
    else
      ((quantity / special.quantity) * special.SpecialPrice) + (quantity % special.quantity) * sku.price
  }

  val getPricePerItem: (SKU, Int) => Double = { (s: SKU, q: Int) =>
    s.special match
      case Some(special) => getSpecialPrice(s, special, q)
      case None => s.price * q
  }

  val getTotalPrice: List[Char] => Double = { b =>
    (for {
      i <- mapBasketAndRemoveNonItems(b, skus)
    } yield getPricePerItem(skus(i._1), i._2)
      )
      .sum
  }

}
