
var id_result = false;
var pw_result = false;
var nick_result = false;
var email_result = false;



$('#user_id').on("change keyup paste", function () {

    const user_id = $('#user_id').val();
    const id_length = user_id.length;
    const id_exp = RegExp(/^[a-z0-9-_]{4,9}$/); // a-z, 0-9, -_, 4자에서 12자 사이

    if (id_length == 0) {
        $('.check-id').hide();
        $('.dup-id').html("필수 입력 항목입니다.");
        $('#user_id').focus();
    } else if (!user_id.match(id_exp)) {
        $('.check-id').hide();
        $('.dup-id').show();
        $('.dup-id').html("4-10자 사이의 영문 소문자와 숫자, 특수 기호 (-)(_) 만 사용 가능합니다.");
    } else if (user_id.match(id_exp)) {
        $.ajax({
            url: '/user/userIdCheck',
            type: 'post',
            data: { user_id: user_id },
            success: function (id_count) {
                if (id_count != 1) {
                    $('.check-id').show();
                    $('.check-id').html('사용 가능한 아이디입니다.');
                    $('.dup-id').hide();

                } else {
                    $('.dup-id').show();
                    $('.dup-id').html('이미 사용 중인 아이디입니다.');
                    $('.check-id').hide();

                }
            }
        });
    }

});


// 유저 비밀번호 일치 여부 확인 및 정규식 검사

$('.u_pw').on("change keyup paste", function () {

    const input_pw = $('.u_pw').val();
    const user_pw = $('#user_pw').val();
    const user_rePw = $('#user_rePw').val();
    const pw_length = user_pw.length;
    // const pw_exp = RegExp(/^[A-Za-z0-9$@$!%*?&]{7,19}$/);
    const pw_exp = RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}$/);

    console.log(user_pw);
    console.log(user_rePw);

    if (pw_length == 0) {
        $('.check-pw').hide();
        $('.cor-pw').html("필수 입력 항목입니다.");
        $('#user_pw').focus();
    } else if (user_pw != user_rePw) {
        $('.check-pw').hide();
        $('.cor-pw').show();
        $('.cor-pw').html("비밀 번호가 일치하지 않습니다.");
    } else if (user_pw == user_rePw) {
        $('.cor-pw').hide();
        $('.check-pw').show();
        $('.check-pw').html("비밀 번호가 일치합니다.");

        if (input_pw.match(pw_exp)) {
            $('.cor-pw').hide();
            $('.check-pw').show();
            $('.check-pw').html("사용 가능한 비밀 번호입니다.");
        } else if (!input_pw.match(pw_exp)) {
            $('.check-pw').hide();
            $('.cor-pw').show();
            $('.cor-pw').html("비밀 번호는 숫자, 영문 대소문자, 특수 문자($@$!%*?&) 조합으로 8-20 자리를 사용해야 합니다.")
        }
    }
});


// 유저 닉네임 정규식 검사 및 중복 확인

$('#user_nick').on("change keyup paste", function () {

    const user_nick = $('#user_nick').val();
    const nick_length = user_nick.length;
    const nick_exp = RegExp(/^[a-z0-9A-Z가-힣-_]{4,9}$/); // 가-힣, a-z, 0-9, -_, 4자에서 10자 사이

    if (nick_length == 0) {
        $('.check-nick').hide();
        $('.dup-nick').html("필수 입력 항목입니다.");
        $('#user_nick').focus();
    } else if (!user_nick.match(nick_exp)) {
        $('.check-nick').hide();
        $('.dup-nick').show();
        $('.dup-nick').html("4-10자 사이의 한글과 영문 대소문자와 숫자, 특수 기호 (-)(_) 만 사용 가능합니다.");
    } else if (user_nick.match(nick_exp)) {
        $.ajax({
            url: '/user/userNickCheck',
            type: 'post',
            data: { user_nick: user_nick },
            success: function (nick_count) {
                if (nick_count != 1) {
                    $('.check-nick').show();
                    $('.check-nick').html('사용 가능한 닉네임입니다.');
                    $('.dup-nick').hide();
                } else {
                    $('.dup-nick').show();
                    $('.dup-nick').html('이미 사용 중인 닉네임입니다.');
                    $('.check-nick').hide();
                }
            }
        });
    }

});


// 유저 이메일 중복 확인 ajax

$('#user_email').keyup(function () {

    const user_email = $('#user_email').val();
    const email_length = user_email.length;
    const email_exp = RegExp(/^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/);

    if (email_length == 0) {
        $('.check-email').hide();
        $('.dup-email').html("필수 입력 항목입니다.");
        $('#user_email').focus();
    } else if (!user_email.match(email_exp)) {
        $('.check-email').hide();
        $('.dup-email').show();
        $('.dup-email').html("이메일 형식에 맞춰 입력해주세요.");
    } else if (user_email.match(email_exp)) {
        $.ajax({
            url: '/user/userEmailCheck',
            type: 'post',
            data: { user_email: user_email },
            success: function (email_count) {
                if (email_count != 1) {
                    $('.check-email').show();
                    $('.check-email').html('사용 가능한 이메일입니다.');
                    $('.dup-email').hide();
                } else {
                    $('.check-email').hide();
                    $('.dup-email').html('이미 사용 중인 이메일입니다.');
                    $('.dup-email').show();
                }
            }
        })
    }


});




$('#userSubmit').click(function () {

    const user_id = $('#user_id').val();
    const user_pw = $('#user_pw').val();
    const user_nick = $('#user_nick').val();
    const user_email = $('#user_email').val();

        // 아이디 공백 검사
        if (user_id == 0 || user_id == '') {
            $('.dup-id').html("필수 입력 항목입니다.");
            $('#user_id').focus();
    
            return false;
        }
    
        // 비밀번호 공백 검사
        if (user_pw == 0 || user_pw == '') {
            $('.cor-pw').html("필수 입력 항목입니다.");
            $('#user_pw').focus();
    
            return false;
        }
    
        // 닉네임 공백 검사
        if (user_nick == 0 || user_nick == '') {
            $('.dup-nick').html("필수 입력 항목입니다.");
            $('#user_nick').focus();
    
            return false;
        }
    
        // 이메일 공백 검사
        if (user_email == 0 || user_email == '') {
            $('.dup-email').html("필수 입력 항목입니다.");
            $('#user_email').focus();
    
            return false;
        }

        $('#join-form').submit();

})



