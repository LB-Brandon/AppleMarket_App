package com.brandon.applemarket_app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brandon.applemarket_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProductAdapter.ItemClick {

    companion object {
        val dummyList = mutableListOf(
            ProductItem(
                id = 1,
                fileName = "sample1",
                productName = "산진 한달된 선풍기 팝니다",
                productInfo = "이사가서 필요가 없어졌어요 급하게 내놓습니다",
                seller = "대현동",
                price = 1000,
                address = "서울 서대문구 창천동",
                likeCount = 13,
                chatCount = 25,
                imageResId = R.drawable.sample1,
            ),
            ProductItem(
                id = 2,
                fileName = "sample2",
                productName = "김치냉장고",
                productInfo = "이사로인해 내놔요",
                seller = "안마담",
                price = 20000,
                address = "인천 계양구 귤현동",
                likeCount = 8,
                chatCount = 28,
                imageResId = R.drawable.sample2,
            ),
            ProductItem(
                id = 3,
                fileName = "sample3",
                productName = "샤넬 카드지갑",
                productInfo = "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다",
                seller = "코코유",
                price = 10000,
                address = "수성구 범어동",
                likeCount = 23,
                chatCount = 5,
                imageResId = R.drawable.sample3,
            ),
            ProductItem(
                id = 4,
                fileName = "sample4",
                productName = "금고",
                productInfo = "금고\n떼서 가져가야함\n대우월드마크센텀\n미국이주관계로 싸게 팝니다",
                seller = "Nicole",
                price = 10000,
                address = "해운대구 우제2동",
                likeCount = 14,
                chatCount = 17,
                imageResId = R.drawable.sample4,
            ),
            ProductItem(
                id = 5,
                fileName = "sample5",
                productName = "갤럭시Z플립3 팝니다",
                productInfo = "갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!",
                seller = "절명",
                price = 150000,
                address = "연제구 연산제8동",
                likeCount = 22,
                chatCount = 9,
                imageResId = R.drawable.sample5,
            ),
            ProductItem(
                id = 6,
                fileName = "sample6",
                productName = "프라다 복조리백",
                productInfo = "까임 오염없고 상태 깨끗합니다\n정품여부모름",
                seller = "미니멀하게",
                price = 50000,
                address = "수원시 영통구 원천동",
                likeCount = 25,
                chatCount = 16,
                imageResId = R.drawable.sample6,
            ),
            ProductItem(
                id = 7,
                fileName = "sample7",
                productName = "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
                productInfo = "울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방3개, 화장실3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다.",
                seller = "굿리치",
                price = 150000,
                address = "남구 옥동",
                likeCount = 142,
                chatCount = 54,
                imageResId = R.drawable.sample7,
            ),
            ProductItem(
                id = 8,
                fileName = "sample8",
                productName = "샤넬 탑핸들 가방",
                productInfo = "샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\n + \"\\n\" + \"색상 : 블랙\\n\" + \"사이즈 : 25.5cm * 17.5cm * 8cm\n + \"구성 : 본품더스트\\n\" + \"\\n\" + \"급하게 돈이 필요해서 팝니다 ㅠ ㅠ",
                seller = "난쉽",
                price = 180000,
                address = "동래구 온천제2동",
                likeCount = 31,
                chatCount = 7,
                imageResId = R.drawable.sample8
            ),
            ProductItem(
                id = 9,
                fileName = "sample9",
                productName = "4행정 엔진분무기 판매합니다.",
                productInfo = "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.",
                seller = "알뜰한",
                price = 30000,
                address = "원주시 명륜2동",
                likeCount = 7,
                chatCount = 28,
                imageResId = R.drawable.sample9
            ),
            ProductItem(
                id = 10,
                fileName = "sample10",
                productName = "셀린느 버킷 가방",
                productInfo = "22년 신세계 대전 구매입니당\n + \"셀린느 버킷백\\n\" + \"구매해서 몇번사용했어요\\n\" + \"까짐 스크래치 없습니다.\\n\" + \"타지역에서 보내는거라 택배로 진행합니당!",
                seller = "똑태현",
                price = 190000,
                address = "중구 동화동",
                likeCount = 40,
                chatCount = 6,
                imageResId = R.drawable.sample10
            )
        )
    }

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val recyclerViewAdapter: ProductAdapter = ProductAdapter(dummyList).apply {
        itemClick = this@MainActivity
    }

    private var itemPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 뒤로가기 버튼의 이벤트 처리
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 다이얼로그 표시
                showExitDialog()
            }
        })
        initViews()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Resume", "update: $itemPosition, Item state: ${dummyList[itemPosition]}")
        recyclerViewAdapter.notifyItemChanged(itemPosition)
    }

    private fun initViews() {
        with(binding) {
            // RecyclerView Adapter
            rvMainProducts.adapter = recyclerViewAdapter
            rvMainProducts.layoutManager = LinearLayoutManager(this@MainActivity)
            rvMainProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        fab.show()
                    } else {
                        fab.hide()
                    }
                }
            })

            // Notification 생성
            ivNotification.setOnClickListener { makeNotification() }

            // Scroll to top
            fab.setOnClickListener { rvMainProducts.smoothScrollToPosition(0) }
        }
    }


    private fun showExitDialog() {
        val builder = AlertDialog.Builder(this@MainActivity)
        with(builder) {
            setIcon(R.drawable.ic_chat)
            setTitle("종료")
            setMessage("정말 종료하시겠습니까?")
            setPositiveButton("확인") { _, _ -> finish() }
            setNegativeButton("취소", null)
            show()
        }
    }

    private fun makeNotification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder = createNotificationBuilder(manager)

        // 알림 세팅
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // 알림 기본 정보
        builder.run {
            setSmallIcon(R.drawable.ic_notification_active)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setStyle(NotificationCompat.BigTextStyle().bigText("이것은 빅 텍스트 스타일 키워드 알림입니다."))
            addAction(R.drawable.ic_notification_active, "실행", pendingIntent)
        }

        // PostNotification 권한 필요
        manager.notify(1, builder.build())
    }

    private fun createNotificationBuilder(manager: NotificationManager): NotificationCompat.Builder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 26 버전 이상
            val channelId = "apple_channel"
            val channelName = "Apple Channel"

            // 채널 생성
            val channel = NotificationChannel(
                channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                // 채널 정보 설정
                description = "Apple Channel Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }

            // 채널을 NotificationManager 에 등록
            manager.createNotificationChannel(channel)
            // 채널 id를 이용하여 builder 생성
            NotificationCompat.Builder(this, channelId)
        } else {
            // 26 버전 이하
            // 채널 정보 없이 builder 생성
            NotificationCompat.Builder(this)
        }
    }

    override fun onClick(view: View, position: Int) {
        Log.d("MainActivity", "Item clicked at position ${dummyList[position]}")
        DetailActivity.moveToDetail(this, dummyList[position])
        itemPosition = position
    }

    override fun onLongClick(view: View, position: Int) {
        val builder = AlertDialog.Builder(this@MainActivity)
        with(builder) {
            setIcon(R.drawable.ic_delete)
            setTitle("상품 삭제")
            setMessage("상품을 정말로 삭제하겠습니까?")
            setPositiveButton("확인") { _, _ ->
                recyclerViewAdapter.removeItem(position)
            }
            setNegativeButton("취소", null)
            show()
        }
    }

}