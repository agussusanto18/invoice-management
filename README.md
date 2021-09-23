# Aplikasi Invoice Management #

Aplikasi ini dipakai untuk mengelola invoice dan menyambungkan dengan berbagai metode pembayaran.
Diantara metode pembayaran yang akan disupport antara lain:

* Virtual Account Bank
  * Bank BNI
  * Bank CIMB
  * Bank BSI

* e-Wallet
  * Ovo
  * GoPay

* QR Payment
  * QRIS


Tipe tagihan yang tersedia:

  * CLOSED : Membayar sesuai nominal, jika tidak sesuai ditolak
  * OPEN : Pembayaran berapapun diterima
  * INSTALLMENT : Pembayaran diterima selama total akumulasi lebih kecil atau sama dengan nominal


Fitur aplikasi:
  
  * Managemen debitur
    * Registrasi debitur
    * Rekap tagihan debitur
    * History pembayaran

  * Managemen invoice
    * Membuat invoice
    * Mengganti nilai dan tanggal jatuh tempo
    * Membatalkan invoice


# Setup Database #
1. Jalankan postgresql di docker
  ```
   docker run --rm \
    --name invoice-db \
    -e POSTGRES_DB=invoicedb \
    -e POSTGRES_USER=admin \
    -e POSTGRES_PASSWORD=admin123 \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -v "$PWD/invoicedb-data:/var/lib/postgresql/data" \
    -p 5432:5432 \
    postgres:12
   ```