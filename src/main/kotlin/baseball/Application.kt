package baseball

import camp.nextstep.edu.missionutils.Randoms

fun main() {
    printGameStart()

    val computerNumber = generateComputerNumber()
    val userNumber = getUserNumber()

    val result = compareNumber(computerNumber, userNumber)
}

fun printGameStart() {
    println("숫자 야구 게임을 시작합니다.")
}

fun generateComputerNumber(): List<Int> {
    val computerNumber = mutableListOf<Int>()
    while (computerNumber.size < 3) {
        val randomNumber = Randoms.pickNumberInRange(1, 9)
        if (!computerNumber.contains(randomNumber)) {
            computerNumber.add(randomNumber)
        }
    }
    return computerNumber
}

fun getUserNumber(): List<Int> {
    print("숫자를 입력해주세요 : ")
    val userInput = readLine()?.toList()

    checkException(userInput)

    val userNumber = mutableListOf<Int>()
    userInput?.forEach { c ->
        userNumber.add(Character.getNumericValue(c))
    }

    return userNumber
}

fun checkException(userInput: List<Char>?) {
    if (userInput?.size != 3) {
        throw IllegalArgumentException("3개의 숫자를 입력하세요")
    }

    userInput.forEach { c ->
        if (c.code < 48 || c.code > 57) {
            throw IllegalArgumentException("숫자만 입력하세요")
        }
    }

    if (userInput.distinct() != userInput) {
        throw java.lang.IllegalArgumentException("서로 다른 수를 입력하세요")
    }
}

fun compareNumber(computerNumber: List<Int>, userNumber: List<Int>): List<Int> {
    val result = mutableListOf<Int>(0, 0)
    for (i in userNumber.indices) {
        if (calcStrike(computerNumber[i], userNumber[i])) {
            result[0]++
        } else {
            result[1] += calcBall(computerNumber, userNumber[i])
        }
    }
    return result
}

fun calcStrike(computerNumber: Int, userNumber: Int): Boolean {
    if (computerNumber == userNumber) return true
    return false
}

fun calcBall(computerNumber: List<Int>, userNumber: Int): Int {
    if (computerNumber.contains(userNumber)) return 1
    return 0
}