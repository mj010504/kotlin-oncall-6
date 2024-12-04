package oncall.utils

fun MutableList<String>.swap(index : Int) {
    val temp = this[index]
    val nextIndex = (index + 1) % this.size
    this[index] = this[nextIndex]
    this[nextIndex] = temp
}