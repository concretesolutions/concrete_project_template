package br.com.concrete.database

import android.content.Context

class DBReposService (var mContext: Context?) {

//    var db: AppDatabase?

    constructor(mContext: Context?, db: AppDatabase?) : this(mContext) {
//        this.db = db
    }

    init {
//        db = getAppDatabase(mContext?.applicationContext)
    }

}