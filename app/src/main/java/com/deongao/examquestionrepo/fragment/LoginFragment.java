package com.deongao.examquestionrepo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.deongao.examquestionrepo.EntityManager;
import com.deongao.examquestionrepo.MainActivity;
import com.deongao.examquestionrepo.R;
import com.deongao.examquestionrepo.contract.LoginContract;
import com.deongao.examquestionrepo.model.ExamQuestion;
import com.deongao.examquestionrepo.presenter.LoginPresenter;
import com.deongao.examquestionrepo.provider.LoginProvider;

public class LoginFragment extends BaseFragment implements LoginContract.View {

    TextInputEditText mUsername, mPwd;
    RadioButton mRbtnAdmin, mRbtnUser;
    Button mBtnLogin, mBtnAdd;

    LoginContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new LoginPresenter(this, new LoginProvider());

        mUsername = view.findViewById(R.id.et_name);
        mPwd = view.findViewById(R.id.et_pwd);
        mRbtnAdmin = view.findViewById(R.id.rbtn_admin);
        mRbtnUser = view.findViewById(R.id.rbtn_user);
        mBtnLogin = view.findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(loginView -> {
            mPresenter.login(mUsername.getText().toString(), mPwd.getText().toString(), mRbtnAdmin.isChecked());
        });

        mBtnAdd = view.findViewById(R.id.btn_add);
        mBtnAdd.setVisibility(View.VISIBLE);
        mBtnAdd.setOnClickListener(view1 -> addQuestions());

    }

    @Override
    public void navigateToAdminPage() {
        ((MainActivity)getActivity()).setAdmin(true);
        getNavigator().navigateToAdminPage();
    }

    @Override
    public void navigateToUserPage() {
        getNavigator().navigateToUserPage();
    }

    @Override
    public void showUsernameEmptyAlert() {
        Toast.makeText(getContext(),R.string.username_empty_alert,Toast.LENGTH_LONG).show();
}

    @Override
    public void showPwdEmptyAlbert() {
        Toast.makeText(getContext(),R.string.pwd_empty_alert,Toast.LENGTH_LONG).show();
    }

    @Override
    String getTitle() {
        return "登录";
    }


    private void addQuestions(){
        addQuestion("在愈伤组织再分化的过程中，下列说法正确的是",
                "先诱导生根，细胞分裂素与生长素的浓度比高",
                "先诱导生根，细胞分裂素与生长素的浓度比低",
                "先诱导生芽，细胞分裂素与生长素的浓度比高",
                "先诱导生芽，细胞分裂素与生长素的浓度比低",
                1,
                "A");

        addQuestion("每次注射青霉素之前，病人都要先做\"皮试\"，其目的是",
                "观察病人的体质以决定用药剂量",
                "观察病人是否患有自身免疫疾病",
                "观察青霉素的杀菌效果",
                "观察病人是否会发生过敏反应",
                1,
                "B");

        addQuestion("下列有关自然界中氮循环的叙述中，错误的是",
                "土壤中的氨经过硝化细菌的作用，最终转化为硝酸盐",
                "在氧气充足的情况下，某些微生物的作用可将硝酸盐最终转化为氮气",
                "植物只能利用土壤中的硝酸盐和铵盐，不能直接利用大气中的氮气",
                "植物根细胞是通过主动运输的方式从土壤中吸收硝酸盐和铵盐的",
                1,
                "C");

        addQuestion("线粒体内膜形成了许多嵴，上面附有大量基粒。经研究发现，基粒是合成ATP所需要的多种酶的复合体。这可以表明，线粒体内膜的重要功能之一是",
                "使物质定向运输 ",
                "控制物质出入线粒体",
                "合成酶蛋白和ATP的场所 ",
                "提供酶的支架，参与反应调控",
                1,
                "D");

        addQuestion("通过显微技术破坏玉米根尖分生区细胞中的高尔基体。培养几天后，再观察该根尖细胞，预测会有什么样的结果",
                "细胞停止分裂",
                "产生多核细胞或多倍体细胞",
                "正常分裂",
                "不能形成纺锤体",
                1,
                "A");

        addQuestion("诱导酶的总量约占细胞蛋白质含量的10%，通过代谢调节，微生物可最经济地利用营养物，并做到既不缺乏也不剩余任何代谢物的高效\"经济核算\"。以下说法错误的是（ ）",
                "由于微生物大多数是单细胞生物，每个细胞都具有全能性，故无基因的选择性表达现象 ",
                "所谓\"既不缺乏也不剩余\"指的是酶合成调节，并不包括酶活性的调节",
                "诱导酶只在有关诱导物存在时才合成 ",
                "通过改变微生物膜的通透性而调节物质出入细胞，这是酶活性的调节",
                1,
                "B");

        addQuestion("如果在\"II\"时期加入适合细菌生存但营养成分不同的培养基，细菌出现的变化是（ ）",
                "若为病菌，此时是感染性最强的时期 ",
                "分裂速度加快，代谢活动加强",
                "出现芽孢，可以产生大量次级代谢产物 ",
                "代谢活跃，细胞内物质的合成速度加快",
                1,
                "C");

        addQuestion("蛋白质是生命活动的体现者，下面有关蛋白质的叙述，不正确的是",
                "硫元素是合成蛋白质的特征元素",
                "为人类研制的全营养液可以含蛋白质",
                "植物吸收的NH4+或NO-3是合成蛋白质的原料",
                "同一个体不同细胞基因相同但合成的蛋白质不同",
                1,
                "D");

        addQuestion("关于微生物所需营养物质的说法错误的是",
                "微生物在生命活动过程中需要碳源、氮源、生长因子、水和无机盐",
                "生物素不是微生物的营养物质",
                "培养自养型微生物的培养基中，不需要加碳源 ",
                "氮源既能为某些微生物提供氮源又能提供能量",
                1,
                "A");

        addQuestion("关于灭菌和消毒的不正确的理解是（ ）",
                "灭菌是指杀灭环境中的一切微生物的细胞、芽孢和孢子",
                "消毒和灭菌实质上是相同的",
                "接种环用烧的方法灭菌 ",
                "常用灭菌方法有加热法、过滤法、紫外线法、化学药品法",
                1,
                "B");

        addQuestion("在培养基的配制过程中，下列哪项操作及作用是不正确的 （ ）",
                "在熔化过程的最后阶段要补加蒸馏水至100ml",
                "趁热将培养基分装到洁净试管中，培养基高度为试管长度的1/5",
                "所加棉塞既能防止杂菌污染，又能通气 ",
                "操作的顺序是称量-熔化-分装-调pH-加棉塞-包扎",
                2,
                "A,B");

        addQuestion("灭菌是发酵工程的一项关键措施，关于灭菌的叙述正确的是",
                "灭菌就是杀死培养基中的一切细菌",
                "培养基和发酵设备均需严格灭菌",
                "通入无菌空气可以达到灭菌的目的",
                "灭菌就是防止或抑制微生物的生长",
                2,
                "A,B,C");

        addQuestion("生态系统中碳循环和氮循环的一部分，A、B、C三类微生物参与其中，下列说法错误的是",
                "A类细菌是自养需氧型",
                "B类细菌是异养厌氧型",
                "进行C过程的微生物都是共生的 ",
                "A和C的活动可增加土壤肥力",
                2,
                "B,C");

        addQuestion("剧烈运动时，血浆中乳酸含量大增而pH基本不变，对此现象的不正确解释是 （ ）",
                "血浆是一种缓冲溶液体系",
                "乳酸与NaHCO3 反应生成弱酸H2CO3",
                "H2CO3分解成的CO2可经呼吸排出 ",
                "大量乳酸进入血液后马上被稀释或被彻底分解",
                2,
                "B,D");

        addQuestion("不属于醛固酮分泌异常引起的不良反应的是",
                "K+在体内储存，心肌自动节律异常",
                "Na+体内储存，造成水肿",
                "腹泻丢掉大量的Na+，血压下降",
                "肾小管排出K+过量，心肌自动节律异常",
                2,
                "B,C,D");

        addQuestion("以下关于细胞的全能性的叙述，正确的是",
                "植物细胞的全能性就是构成植物体的每一个细胞都参与植物体的每一项生命活动",
                "植物细胞的全能性就是构成植物体的每一个细胞形态结构都一样",
                "植物细胞的全能性就是构成植物体的每一个活细胞都可能分化发育成一株完整的植物体",
                "植物细胞的全能性就是一个植物体都只由一个细胞分化而来构成",
                2,
                "A,B");

        addQuestion("细胞质是细胞结构的重要组成部分，下列生物化学反应不在细胞质中进行的是 （ ）",
                "葡萄糖的无氧分解",
                "丙酮酸的氧化分解",
                "核糖体RNA的合成 ",
                "各种消化酶的合成",
                2,
                "A,B,C");

        addQuestion("经常松土能提高农作物的产量，下列说法与之不符的是( )",
                "增强植物的呼吸作用，为吸收矿质离子提供更多的能量",
                "促进硝化细菌将氨态氮转化为硝态氮，提高氮肥的利用率",
                "有利于微生物分解腐殖质，提高植物光合作用的效率",
                "促进根系吸收有机肥料，多级利用能量",
                2,
                "A,B,C,D");

        addQuestion("若用胃液和肠液混合后处理果蝇唾液腺巨大染色体，其结果可能是：",
                "染色体上蛋白质被胃蛋白酶和肠肽酶催化水解成氨基酸",
                "因无水解核酸的酶，则留下一条细链称为DNA",
                "蛋白质和DNA都被水解成其基本单位氨基酸和核苷酸 ",
                "不能被水解，仍为果蝇唾液腺巨大染色体",
                2,
                "A,B,C,D");

        addQuestion("下面对生物工程的应用成果说法正确的是",
                "用白血病患者细胞中分离出的癌基因制备DNA探针可检测肝炎",
                "鸡蛋白基因可在大肠杆菌或酵母菌中表达出卵清蛋白",
                "在单抗上连接抗癌药物可制成定向消灭癌细胞的\"生物导弹\" ",
                "利用发酵工程可获得大量微生物的代谢产物即单细胞蛋白",
                2,
                "A,B,C,D");

        addQuestion("任何生物都能独立地具备生物的几个基本特征，并符合细胞学说",
                "",
                "",
                "",
                "",
                3,
                "B");

        addQuestion("不同的生物体内，组成生物体的化学元素种类大体相同，各种化学元素的含量相差很大",
                "",
                "",
                "",
                "",
                3,
                "A");

        addQuestion("任何生物都能独立地具备生物的几个基本特征，并符合细胞学说",
                "",
                "",
                "",
                "",
                3,
                "B");

        addQuestion("构成细胞的任何一种化合物都能在无机自然界中找到。",
                "",
                "",
                "",
                "",
                3,
                "B");

        addQuestion("淀粉、半乳糖以及糖原的元素组成都是相同的。",
                "",
                "",
                "",
                "",
                3,
                "A");

        addQuestion("蔗糖和淀粉不能用本尼迪特来检测。果糖没有醛基但是也可以用本尼迪特来检测。本尼迪特的检测需要使用水浴加热。",
                "",
                "",
                "",
                "",
                3,
                "A");

        addQuestion("地震灾害后，灾民啃食树皮和草，通过消化纤维素来给机体供能。",
                "",
                "",
                "",
                "",
                3,
                "B");

        addQuestion("在小鼠的口腔细胞中可以检测到麦芽糖。",
                "",
                "",
                "",
                "",
                3,
                "B");

        addQuestion("糖原的代谢产物是葡萄糖，蛋白质的代谢产物是氨基酸。",
                "",
                "",
                "",
                "",
                3,
                "B");

        addQuestion("脂质只由C、H、O 元素组成，可与糖类之间相互转换。",
                "",
                "",
                "",
                "",
                3,
                "B");

        addQuestion("成蛋白质的氨基酸都只含有一个氨基与一个羧基，并且连接在同一个碳原子上；每一条肽链至少含有一个游离的氨基与一个游离的羧基。",
                "",
                "",
                "",
                "",
                3,
                "B");



    }


    private void addQuestion(String title, String a, String b, String c, String d, int type, String answer){
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setAnswerA(a);
        examQuestion.setAnswerB(b);
        examQuestion.setAnswerC(c);
        examQuestion.setAnswerD(d);
        examQuestion.setTitle(title);
        examQuestion.setType(type);
        examQuestion.setRealAnswer(answer);

        EntityManager.getInstance().addQuestion(examQuestion);
    }
}
