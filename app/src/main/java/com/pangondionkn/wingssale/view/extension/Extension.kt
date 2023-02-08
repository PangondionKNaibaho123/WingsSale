package com.pangondionkn.wingssale.view.extension

class Extension {
    interface NUMBERING_FORMAT{
        companion object{
            fun Int.formatThousandSeparator(): String {
                return toString()
                    .reversed()
                    .chunked(3)
                    .joinToString(".")
                    .reversed()
            }
        }
    }
}