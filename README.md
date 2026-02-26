# GUI Library Management System

Aplikasi manajemen perpustakaan berbasis GUI menggunakan Java Swing dengan arsitektur Model-View-Controller (MVC).

## 📋 Tentang Proyek

Proyek ini merupakan aplikasi desktop untuk manajemen perpustakaan yang dikembangkan sebagai tugas mata kuliah **Object-Oriented Programming (OOP) Java**. Aplikasi ini memungkinkan pengelolaan data buku, anggota perpustakaan, dan transaksi peminjaman/pengembalian buku dengan antarmuka grafis yang user-friendly.

## 👥 Tim Pengembang

- **Putra Fajar Indra Setiawan**
- **Muhammad Aufar Maulana**
- **Taufik Darmawan**

## ✨ Fitur Utama

- 📚 **Manajemen Buku**: Tambah, edit, hapus, dan cari data buku
- 👤 **Manajemen Anggota**: Kelola data anggota perpustakaan
- 🔄 **Peminjaman Buku**: Proses peminjaman buku oleh anggota
- ✅ **Pengembalian Buku**: Proses pengembalian dan tracking status buku
- 🔍 **Pencarian**: Fitur pencarian buku dan anggota
- 📊 **Laporan**: Generate laporan transaksi perpustakaan

## 🏗️ Arsitektur Aplikasi

Aplikasi ini dibangun menggunakan pola desain **MVC (Model-View-Controller)**:

```
src/
├── model/          # Business logic dan data model
│   ├── Buku.java
│   ├── Anggota.java
│   └── Peminjaman.java
├── view/           # GUI components (Java Swing)
│   ├── MainFrame.java
│   └── [Form views]
└── controller/     # Controller untuk menghubungkan Model dan View
    └── [Controllers]
```

### Konsep OOP yang Diimplementasikan

- ✅ **Encapsulation**: Data disembunyikan dalam class dengan getter/setter
- ✅ **Inheritance**: Hierarki class untuk reusability
- ✅ **Polymorphism**: Method overriding dan overloading
- ✅ **Abstraction**: Interface dan abstract class untuk struktur yang flexible

## 🛠️ Teknologi yang Digunakan

- **Java SE** (Standard Edition)
- **Java Swing** - untuk GUI
- **NetBeans IDE** - development environment
- **Ant** - build tool

## 📦 Instalasi dan Menjalankan Aplikasi

### Prerequisites

- Java Development Kit (JDK) 8 atau lebih tinggi
- NetBeans IDE (opsional, untuk development)

### Cara Menjalankan

#### Menggunakan NetBeans:

1. Clone repository ini:
   ```bash
   git clone https://github.com/Patra-fjr/GUI-LibraryJavaProject.git
   ```

2. Buka NetBeans IDE

3. Pilih `File` → `Open Project`

4. Navigasi ke folder project dan buka

5. Klik kanan pada project → `Run`

#### Menggunakan Command Line:

1. Clone repository:
   ```bash
   git clone https://github.com/Patra-fjr/GUI-LibraryJavaProject.git
   cd GUI-LibraryJavaProject
   ```

2. Build project:
   ```bash
   ant clean
   ant compile
   ant jar
   ```

3. Jalankan aplikasi:
   ```bash
   java -jar dist/GUI-LibraryJavaProject.jar
   ```

## 📸 Screenshot

<!-- Tambahkan screenshot aplikasi Anda di sini -->
_Screenshot aplikasi akan ditambahkan_

## 📚 Struktur Database/Data

Aplikasi ini menyimpan data dalam struktur berikut:
- Data Buku (ID, Judul, Pengarang, Penerbit, Tahun, Status)
- Data Anggota (ID, Nama, Alamat, Telepon, Email)
- Data Peminjaman (ID Transaksi, ID Buku, ID Anggota, Tanggal Pinjam, Tanggal Kembali)

## 🔜 Pengembangan Selanjutnya

- [ ] Implementasi database (MySQL/PostgreSQL)
- [ ] Sistem login dan autentikasi
- [ ] Export laporan ke PDF/Excel
- [ ] Sistem denda keterlambatan
- [ ] Notifikasi jatuh tempo pengembalian

## 📄 Lisensi

Proyek ini dibuat untuk keperluan akademik sebagai tugas mata kuliah OOP Java.

## 📞 Kontak

Untuk pertanyaan atau saran, silakan hubungi tim pengembang melalui Issues di repository ini.

---

**Dibuat dengan ❤️ oleh Tim OOP Java**