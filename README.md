# Ứng dụng UTH Smart Task

- Chức năng

  - Khả năng điều hướng cơ bản giữa các màn hình
  - Thiết kế các màn hình hướng dẫn với các nút điều hướng, khả năng skip để đi đến trực tiếp màn hình chính
  - BottomNavBar giúp điều khiển giữa các màn hình khác nhau, FloatingActionButton hiển thị icon nổi lên
  - Cấu Trúc Scaffold
    - Cung cấp một bố cục cơ bản cho ứng dụng với các thành phần như AppBar, BottomNavigation, FloatingActionButton.
    - Tạo ra một giao diện người dùng nhất quán và dễ sử dụng.
  - Floating Action Button (FAB)
    - Cho phép người dùng thực hiện các hành động chính như thêm nhiệm vụ mới.
    - Có thể tùy chỉnh với các biểu tượng và màu sắc khác nhau để thu hút sự chú ý.
  - Bottom Navigation
    - Cung cấp cách điều hướng nhanh giữa các màn hình chính như "Nhiệm vụ", "Thông báo", và "Cài đặt".
    - Hỗ trợ người dùng dễ dàng chuyển đổi giữa các phần của ứng dụng mà không cần quay lại màn hình trước.
  - Top Navigation
    - Hiển thị tiêu đề màn hình và các tùy chọn khác như tìm kiếm hoặc bộ lọc.
    - Giúp người dùng dễ dàng nhận diện vị trí của mình trong ứng dụng.
  - Card hiển thị theo độ ưu tiên
    - Sử dụng Card để hiển thị các nhiệm vụ hoặc thông báo với độ ưu tiên khác nhau.
    - Các thẻ có thể được phân loại theo màu sắc hoặc biểu tượng để thể hiện mức độ ưu tiên (cao, trung bình, thấp).
  - LazyColumn giúp người dùng scroll một cách dễ dàng.
  - Thực hiện gọi API hiển thị trục tiếp, có màn hình error hiển thị khi API bị lỗi, cùng với call api get id hiển thị chi tiết các thành phần tương ứng.

- Công nghệ sử dụng:
  - Jetback Compose
    - Sử dụng để xây dựng giao diện người dùng một cách nhanh chóng và hiệu quả.
    - Hỗ trợ tạo các thành phần UI tương tác và động.
  - NavController
    - Quản lý các chuyển đổi giữa các màn hình trong ứng dụng.
    - Dễ dàng tích hợp với Jetpack Compose để điều hướng mượt mà.
  - AnimatedNavHost tạo hiệu ứng chuyển cảnh khi chuyển từ Splash Screen vào Home Screen
    - Tạo hiệu ứng chuyển cảnh mượt mà giữa các màn hình.
    - Cải thiện trải nghiệm người dùng khi di chuyển giữa các màn hình.
  - LaunchedEffect tự động chuyển màn hình với thời gian chỉ định
    - Sử dụng để tự động chuyển màn hình sau một khoảng thời gian nhất định.
    - Giúp tạo ra trải nghiệm liền mạch cho người dùng.
  - Floating Action Button (FAB) làm nổi bật vị trí trên màn hình, giúp người dùng dễ dàng nhận diện và tương tác.
  - Lazycolumn khả năng scroll mạnh mẽ, tạo view vừa đủ để hiển thị, tối ưu hơn Column với khả năng hiển thị dữ liệu lớn.
  - Thư viện Retrofit cung cấp khả năng gọi API tiện lợi, dễ sử dụng

# Giao diện
<img width="200" alt="Screenshot_20250315_230245" src="https://github.com/user-attachments/assets/550a05cc-da34-4770-9ef2-defcc1015483" />
<img width="200" alt="Screenshot_20250315_225856" src="https://github.com/user-attachments/assets/6cefaf85-9eb3-461a-b86f-4bd9472525ed" />
<img width="200" alt="Screenshot_20250315_225909" src="https://github.com/user-attachments/assets/fe325f29-6d63-412f-9da8-ce33eeaf4c42" />
<img width="200" alt="Screenshot_20250315_225919" src="https://github.com/user-attachments/assets/a9c03213-bb84-4d74-92fc-355b815437f4" />
