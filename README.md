## 기능 목록

#### 비상 근무 정보 입력
- 올바르지 않은 입력을 할 경우 [ERROR]로 시작하는 에러 메시지를 출력 후 다시 입력받는다.

### 비상 근무 배정할 월과 시작 요일 입력
- 비상 근무를 배정할 월과 시작 요일 입력

### 비상 근무 순번 입력
- 평일 비상 근무 순번 입력
- 휴일 비상 근무 순번 입력
- 평일 순번 또는 휴일 순번의 입력 값이 올바르지 않은 경우, '평일 순번'부터 다시 입력받는다.
- 비상 근무자는 평일 순번, 휴일 순번에 각각 1회 편성되어야 한다.

### 비상 근무표 출력
- 01일 부터 달의 마지막 일까지 근무표를 출력한다. (2월은 28일까지)
- 평일이면서 법정공휴일의 경우에만 요일 뒤에 (휴일)을 표기한다.
- 순번상 특정 근무자가 연속 2일 근무하게 되는 상황에는, 다음 근무자와 순서를 바꿔 편성한다.