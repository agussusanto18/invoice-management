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
    postgres:13
   ```