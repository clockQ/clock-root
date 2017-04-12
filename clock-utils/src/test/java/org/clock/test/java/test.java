package org.clock.test.java;


public class test {
	public static void main(String[] args) {
		BSTreeNode node5 = new BSTreeNode(5, null, null);
		BSTreeNode node7 = new BSTreeNode(7, null, null);
		BSTreeNode node9 = new BSTreeNode(9, null, null);
		BSTreeNode node11 = new BSTreeNode(11, null, null);
		BSTreeNode node6 = new BSTreeNode(6, node5, node7);
		BSTreeNode node10 = new BSTreeNode(10, node9, node11);
		BSTreeNode node8 = new BSTreeNode(8, node6, node10);
		fun(node8);
	}

	public static void fun(BSTreeNode node){
		if(node.m_pLeft != null && node.m_pRight != null){
			if(node.m_pLeft.m_nValue < node.m_pRight.m_nValue){
				BSTreeNode temp = node.m_pRight;
				node.m_pRight = node.m_pLeft;
				node.m_pLeft = temp;
			}
			System.out.println(node.m_nValue);
			System.out.println(node.m_pLeft.m_nValue + "    " + node.m_pRight.m_nValue);
			fun(node.m_pLeft);
			fun(node.m_pRight);
		}
	}
}
class BSTreeNode{
	int m_nValue;
	BSTreeNode m_pLeft;
	BSTreeNode m_pRight;

	public BSTreeNode(int m_nValue,BSTreeNode m_pLeft,BSTreeNode m_pRight){
		this.m_nValue = m_nValue;
		this.m_pLeft = m_pLeft;
		this.m_pRight = m_pRight;
	}
}
