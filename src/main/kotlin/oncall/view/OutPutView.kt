package oncall.view

object OutPutView {
    fun printOnCallGraph(graph : MutableList<Pair<String, String>>) {
        var onCallGraph = ""
        for(lable in graph) {
            onCallGraph += "${lable.first} ${lable.second}\n"
        }
        println(onCallGraph)
    }

}