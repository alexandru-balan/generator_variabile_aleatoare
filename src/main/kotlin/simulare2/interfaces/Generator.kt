package simulare2.interfaces

/**
 * @author Alexandru Balan -- last modified : 2020/04/10
 * @since 2020.alpha.1
 *
 * Interfata aceasta trebuie implementata de toate clasele care doresc sa genereze valori de tip "sample" pentru
 * functia lor de repartitie
 */
interface Generator {
    /**
     * Aceasta metoda e folosita pentru generarea valorilor fara insa a oferi un rezultat in exterior
     *
     * @param size : [Int] Numarul de valori care se genereaza
     */
    fun generate (size : Int)
}