import random

from Words import wordDatabase


# Node tree: node tree that stores all of the words from wordDatabase

# Initialize node tree
class Node:
    def __init__(self, value):
        self.value = value
        self.nodeList = []
        self.isWord = False
    
    def searchNodeList(self, value):
        for node in self.nodeList:
            if node.value == value:
                return node

def createTree():
    for word in wordDatabase:
        node = root
        for letter in word:
            letter = letter.upper()
            if node.searchNodeList(letter):
                node = node.searchNodeList(letter)
            else:
                newNode = Node(letter)
                node.nodeList.append(newNode)
                node = newNode
        node.isWord = True

root = Node('root')
createTree()

# Testing and saving words

wordsInGame = []    # Stores all of the words found in the gameboard

# Tests if string is a word in the node tree
# Stores to wordsInGame if it is
def testWord(word):
    node = root
    for letter in word:
        if node.searchNodeList(letter):
            node = node.searchNodeList(letter)
        else:
            return print(word + " is not in the tree")
    print(word + " is in the tree")

    if node.isWord:
        wordsInGame.append(word)

# Game Board: returns a 4x4 array of random letters

# gameBoard = [
#     ['', '', '', ''],
#     ['', '', '', ''],
#     ['', '', '', ''],
#     ['', '', '', '']
#     ]

# letterList = [
#     'A', 'B', 'C', 'D', 'E', 'F', 'G', 
#     'H', 'I', 'J', 'K', 'L', 'M', 'N', 
#     'O', 'P', 'Q', 'R', 'S', 'T', 'U', 
#     'V', 'W', 'X', 'Y', 'Z']

# random.seed()
# for rowIndex, row in enumerate(gameBoard):
#     for colIndex, col in enumerate(row):
#         gameBoard[rowIndex][colIndex] = letterList[random.randint(0, 25)]

gameBoard = [
    ['F', 'E', 'A', 'R'],
    ['A', 'H', 'J', 'I'],
    ['L', 'H', 'I', 'D'],
    ['S', 'F', 'J', 'B']
    ]

# Searches the gameBoard for words

# checks if a space is already used in the word
alreadyChecked = [
    [False, False, False, False],
    [False, False, False, False],
    [False, False, False, False],
    [False, False, False, False]
]

def checkSurrounding(row, col, booleanBoard, string, value, node: Node):

    booleanBoard[row][col] = True
    node = node.searchNodeList(value)
    string = string + value

    surroundingList = [[-1,0],[-1,1],[0,1],[1,1],[1,0],[1,-1],[0,-1],[-1,-1]]
    for coordinate in surroundingList:
        newRow = row + coordinate[0]
        newCol = col + coordinate[1]

        if -1 < newRow < 4 and -1 < newCol < 4:
            value = gameBoard[newRow][newCol]
            print("testing " + string + value)
            if booleanBoard[newRow][newCol] == False and node.searchNodeList(value):
                testWord(string)
                checkSurrounding(newRow, newCol, booleanBoard, string, value, node)

def game():
    for row, rowVal in enumerate(gameBoard):
        for col, colVal in enumerate(rowVal):
            print("testing" + str(row) + " " + str(col))
            val = gameBoard[row][col]
            checkSurrounding(row, col, alreadyChecked, "", val, root)
            print()
game()
print(wordsInGame)
