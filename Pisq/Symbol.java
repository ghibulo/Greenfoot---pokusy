public enum Symbol{
    nic, krizek, kolecko, nicSel, krizekSel, koleckoSel;
    public Symbol neSel() {
        if (this==nicSel) return nic;
        if (this==krizekSel) return krizek;
        if (this==koleckoSel) return kolecko;
        return this;
    }
}
