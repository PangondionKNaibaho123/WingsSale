package com.pangondionkn.wingssale.view.extension

import java.util.*

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

    interface TIME{
        companion object{
            fun getCurrentDateTime(): Date {
                return Calendar.getInstance().time
            }
        }
    }
}