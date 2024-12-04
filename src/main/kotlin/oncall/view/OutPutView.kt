package oncall.view

object OutPutView {
    fun printOnCallGraph(graph : MutableList<Pair<String, String>>) {
        for(lable in graph) {
            println("${lable.first} ${lable.second}")
        }
    }
}