package com.tournament.data.golf.adapters.persistence.entity

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.type.EnumType
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Types
import kotlin.jvm.Throws

class PostgresSQLEnumType : EnumType<Enum<*>>() {

    @Throws(HibernateException::class, SQLException::class)
    override fun nullSafeSet(
        st: PreparedStatement,
        value: Any,
        index: Int,
        session: SharedSessionContractImplementor
    ) {
        st.setObject(
            index,
            value.toString(),
            Types.OTHER
        )
    }
}
