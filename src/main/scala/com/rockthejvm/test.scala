package com.rockthejvm





sealed abstract class Tree[A]
case class Empty[A]() extends Tree[A]
case class Node[A](left: Tree[A], root: A, right: Tree[A]) extends Tree[A]

object Tree{
  def void[A]: Tree[A] = Empty()
  def leaf[A](a: A): Node[A] = Node(Empty(), a, Empty())
  def right[A](a: A, tree: Tree[A]): Node[A] = Node(Empty(), a, tree)
  def left[A](tree: Tree[A], a: A): Node[A] = Node(tree, a, Empty())
  def node[A](left: Tree[A], a: A, right: Tree[A]): Node[A] = Node(left, a, right)
}

import com.rockthejvm.Tree._

def foldTree[A, B](tree: Tree[A])(empty: B)(node: (B, A, B) => B): B =
  tree match {
    case Empty() => empty
    case Node(left, root, right) => node(foldTree(left)(empty)(node), root, foldTree(right)(empty)(node))
  }

object Signatures{
  abstract class List[A]{

    // Common HOFs
    def foldRight[B](nil: B)(cons: (A, B) => B): B
    def foldLeft[B](initial: B)(update: (B, A) => B): B
    def map[B](f: A => B): List[B]
    def flatMap[B](f: A => List[B]): List[B]
    def filter(f: A => Boolean): List[A]
    def forall(pred: A => Boolean): Boolean
    def exists(pred: A => Boolean): Boolean

    // Returns the number of elements of this list
    def length: Int
    def reverse: List[A]
  }
}

class test {

}
class TestBreadth(breadth: Tree[Int] => Int => List[Int]) extends FlatSpec with Matchers{
  "breadth path" should "work" in {
    List(0,1) map breadth(void) shouldBe
      List(Nil, Nil)

    List(0,1,2,3,4,5) map breadth(left(left(right(3,right(2,leaf(1))), 4), 5)) shouldBe
      List(List(5), List(4), List(3), List(2), List(1), Nil)

    List(0,1,2,3,4,5) map breadth(node(left(leaf(4), 1), 0, node(leaf(3), 2, right(2, right(4, leaf(5)))))) shouldBe
      List(List(0), List(1, 2), List(4, 3, 2), List(4), List(5), Nil)
  }

  def breadth[A](tree: Tree[A]): Int => List[A] =
    level => tree match {
      case Empty() => List()
      case Node(_, root, _) if level == 0 => List(root)
      case Node(left, _, right) =>
        breadth(left)(level - 1) ++ breadth(right)(level - 1)
    }

  def breadth[A](tree: Tree[A])(level: Int): List[A] =
    tree match {
      case Empty() => List()
      case Node(_, root, _) if level == 0 => List(root)
      case Node(left, _, right) =>
        breadth(left)(level - 1) ++ breadth(right)(level - 1)
    }

  def breadth[A](tree: Tree[A]): Int => List[A] = {
    def aux(tree: Tree[A], level: Int, acc: List[A]): List[A] =
      tree match {
        case Empty() => acc
        case Node(_, root, _) if level == 0 => root :: acc
        case Node(left, _, right) =>
          aux(left, level - 1, aux(right, level - 1, acc))
      }

    level => aux(tree, level, List())
  }
}
