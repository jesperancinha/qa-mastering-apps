package org.jesperancinha.narwhals.safe

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlAttribute
import jakarta.xml.bind.annotation.XmlType
import org.jesperancinha.narwhals.NarwhalInterface
import org.jesperancinha.narwhals.NarwhalsInterface
import java.math.BigDecimal

@JsonRootName("narwhals")
data class Narwhals(
    @param:JsonAlias("narwhal")
    override val narwhal: List<Narwhal>,
) : NarwhalsInterface<Narwhal>

@JsonRootName("narwhal")
data class Narwhal(
    @param:JsonProperty
    override val name: String,
    @param:JsonProperty
    override val age: BigDecimal,
    @param:JsonProperty
    override val sex: String,
) : NarwhalInterface<BigDecimal>

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XmlNarwhals", propOrder = ["narwhal"])
data class XmlNarwhals(
    override var narwhal: List<XmlNarwhal> = mutableListOf(),
) : NarwhalsInterface<NarwhalInterface<BigDecimal>>

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XmlNarwhal")
data class XmlNarwhal(
    @XmlAttribute(name = "name")
    override var name: String? = null,
    @XmlAttribute(name = "age")
    override var age: BigDecimal? = null,
    @XmlAttribute(name = "sex")
    override var sex: String? = null,
) : NarwhalInterface<BigDecimal>

fun ElapsedDays.dailyCabbages(): BigDecimal = BigDecimal(1200).subtract(this.multiply(BigDecimal(0.06)))

fun ElapsedDays.tusksFall(): BigDecimal = BigDecimal(200).add(this.multiply(BigDecimal(0.01)))
