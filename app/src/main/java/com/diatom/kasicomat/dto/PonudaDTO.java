package com.diatom.kasicomat.dto;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

public class PonudaDTO implements Parcelable {
    @DrawableRes private int slikaId;
    private String naziv;
    private int cena;

    public PonudaDTO() {

    }

    public PonudaDTO(int slikaId, String naziv, int cena) {
        this.slikaId = slikaId;
        this.naziv = naziv;
        this.cena = cena;
    }

    public int getSlikaId() {
        return slikaId;
    }

    public void setSlikaId(int slikaId) {
        this.slikaId = slikaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(slikaId);
        dest.writeString(naziv);
        dest.writeInt(cena);
    }

    public static final Parcelable.Creator<PonudaDTO> CREATOR = new Parcelable.Creator<PonudaDTO>() {
        @Override
        public PonudaDTO createFromParcel(Parcel source) {
            return new PonudaDTO(source);
        }

        @Override
        public PonudaDTO[] newArray(int size) {
            return new PonudaDTO[size];
        }
    };

    private PonudaDTO(Parcel in) {
        slikaId = in.readInt();
        naziv = in.readString();
        cena = in.readInt();
    }
}
